package com.project.rentalcar.service.impl;

import com.project.rentalcar.common.exception.CustomException;
import com.project.rentalcar.common.payload.ResponseCode;
import com.project.rentalcar.common.utils.JwtUtils;
import com.project.rentalcar.mapper.UserMapper;
import com.project.rentalcar.model.dto.request.*;
import com.project.rentalcar.model.dto.response.RegistrationResponse;
import com.project.rentalcar.model.dto.response.UserDashboardResponse;
import com.project.rentalcar.model.dto.response.UserDetailResponse;
import com.project.rentalcar.model.dto.response.UserResponse;
import com.project.rentalcar.model.entity.EmailTemplateName;
import com.project.rentalcar.model.entity.User;
import com.project.rentalcar.model.entity.UserInfo;
import com.project.rentalcar.model.entity.UserOtp;
import com.project.rentalcar.repository.RoleRepository;
import com.project.rentalcar.repository.BookingRepository;
import com.project.rentalcar.repository.CarRepository;
import com.project.rentalcar.repository.FavoriteRepository;
import com.project.rentalcar.repository.NotificationRepository;
import com.project.rentalcar.repository.UserInfoRepository;
import com.project.rentalcar.repository.UserOtpRepository;
import com.project.rentalcar.repository.UserRepository;
import com.project.rentalcar.repository.WalletRepository;
import com.project.rentalcar.service.EmailService;
import com.project.rentalcar.service.RedisService;
import com.project.rentalcar.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final EmailService emailService;
    private final UserOtpRepository userOtpRepository;
    private final RedisService redisService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;
    private final FavoriteRepository favoriteRepository;
    private final NotificationRepository notificationRepository;
    private final WalletRepository walletRepository;
    private final JwtUtils jwtUtils;

    @Value("${application.character.value}")
    private String character;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Value("${application.file.uploads.photos-output-path}")
    private String uploadsPath;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {
        try {
            var user = userRepository.findByEmail(request.getEmail());
            if (user.isPresent()) {
                throw new CustomException(ResponseCode.EMAIL_ALREADY_EXISTS);
            }

            var role = roleRepository.findRoleByName(request.getRole());
            if (role.isEmpty()) {
                throw new CustomException(ResponseCode.ROLE_NOT_FOUND);
            }

            var userInfo = userMapper.toUserInfo(request);
            if (userInfo == null) {
                log.error("Failed to map registration request to UserInfo");
                throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
            }
            userInfo.setId(UUID.randomUUID().toString());
            userInfoRepository.save(userInfo);

            var newUser = userMapper.toUser(request);
            if (newUser == null) {
                log.error("Failed to map registration request to User");
                throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
            }
            newUser.setId(UUID.randomUUID().toString());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setEnabled(false);
            newUser.setLoginCount(0);
            newUser.setLocked(false);
            newUser.setRoles(Set.of(role.get()));
            newUser.setUserInfo(userInfo);

            userRepository.save(newUser);

            sendValidationEmail(newUser);
            return userMapper.toRegistrationResponse(userInfo);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during registration", e);
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    public void changePassword(ChangePasswordRequest request, Authentication authentication) {
        try {
            var user = (User) authentication.getPrincipal();

            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new CustomException(ResponseCode.INVALID_CURRENT_PASSWORD);
            }

            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                throw new CustomException(ResponseCode.PASSWORD_CONFIRM_NOT_MATCH);
            }

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);

            emailService.sendEmail(
                    user.getUserInfo().getEmail(),
                    user.getUserInfo().getFullName(),
                    EmailTemplateName.ACTIVATE_ACCOUNT,
                    activationUrl,
                    "Change password successfully. Changed password: " + request.getNewPassword(),
                    "Change password"
            );
        } catch (CustomException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Unexpected error during password change", e);
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void handleForgotPassword(ForgotPasswordRequest request) {
        try {
            var user = userRepository.findByEmailAndIsDeleted(request.getEmail())
                    .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

            String otp = generateAndActivateCode(user);
            redisService.setValue("otp:" + user.getUserInfo().getEmail(), otp, 5, TimeUnit.MINUTES);

            sendValidationEmail(user);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during forgot password", e);
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void verifyForgotPassword(ForgotPasswordVerifyRequest request) {
        try {
            var userOpt = userRepository.findByEmailAndIsDeleted(request.getEmail())
                    .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

            var userEmail = userOpt.getUserInfo().getEmail();
            var otpKey = "otp:" + userEmail;
            var otpInput = request.getOtp();

            var otpInRedis = redisService.getValue(otpKey).toString();

            // B3: Xác thực OTP
            if (otpInRedis != null) {
                // So sánh OTP nhập vào với Redis
                if (!otpInRedis.equals(otpInput)) {
                    throw new CustomException(ResponseCode.INVALID_OTP);
                }
                // Nếu đúng → xóa Redis (tránh reuse)
                redisService.delete(otpKey);
            } else {
                // Nếu Redis đã hết TTL → fallback kiểm tra DB
                var otpInDbOpt = userOtpRepository.findValidOtp(userEmail, otpKey)
                        .orElseThrow(() -> new CustomException(ResponseCode.INVALID_OTP));

                if (otpInDbOpt.isUsed()) {
                    throw new CustomException(ResponseCode.OTP_ALREADY_USED);
                }

                if (LocalDateTime.now().isAfter(otpInDbOpt.getExpiresAt())) {
                    throw new CustomException(ResponseCode.EXPIRED_OTP);
                }

                if (!otpInDbOpt.getOtpCode().equals(otpInput)) {
                    throw new CustomException(ResponseCode.INVALID_OTP);
                }

                otpInDbOpt.setUsed(true);
                otpInDbOpt.setValidatedAt(LocalDateTime.now());
                userOtpRepository.save(otpInDbOpt);
            }

            var newPassword = generateRandomPassword();
            userOpt.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userOpt);

            sendChangePasswordEmail(userOpt, newPassword);
        } catch (CustomException e) {
            throw e ;
        } catch (Exception e) {
            log.error("Unexpected error during forgot password verification", e);
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendChangePasswordEmail(User userOpt, String newPassword) {
        try {
            emailService.sendEmail(
                    userOpt.getUserInfo().getEmail(),
                    userOpt.getUserInfo().getFullName(),
                    EmailTemplateName.ACTIVATE_ACCOUNT,
                    activationUrl,
                    newPassword,
                    "New password"
            );
        } catch (Exception e) {
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void activateAccount(String validOtp) {
        try {
            var otp = userOtpRepository.findByOtpCode(validOtp)
                    .orElseThrow(() -> new CustomException(ResponseCode.INVALID_OTP));

            if(LocalDateTime.now().isAfter(otp.getExpiresAt())) {
                sendValidationEmail(otp.getUser());
                throw new CustomException(ResponseCode.EXPIRED_OTP);
            }

            var user = otp.getUser();
            if(user == null) {
                throw new CustomException(ResponseCode.USER_NOT_FOUND);
            }

            user.setEnabled(true);
            userRepository.save(user);
            otp.setValidatedAt(LocalDateTime.now());
            otp.setUsed(true);
            userOtpRepository.save(otp);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during account activation", e);
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    public List<UserResponse> getAllUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new CustomException(ResponseCode.ACCESS_DENIED);
        }

        List<UserInfo> userInfos = userInfoRepository.findAll();
        return userInfos.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse updateProfile(UserProfileUpdateRequest request, Authentication authentication) {
        var user = getCurrentUser(authentication);
        var userInfo = user.getUserInfo();

        userInfo.setFirstName(request.getFirstName());
        userInfo.setLastName(request.getLastName());
        userInfo.setPhoneNumber(request.getPhoneNumber());
        userInfo.setAddress(request.getAddress());
        userInfo.setDepartment(request.getDepartment());
        userInfo.setGender(request.getGender());

        userInfoRepository.save(userInfo);
        userRepository.save(user);
        return userMapper.toUserResponse(userInfo);
    }

    @Override
    public UserDetailResponse getUserById(String id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        return UserDetailResponse.builder()
                .profile(userMapper.toUserResponse(user.getUserInfo()))
                .enabled(user.isEnabled())
                .locked(user.isLocked())
                .loginCount(user.getLoginCount())
                .status(user.getStatus())
                .build();
    }

    @Override
    @Transactional
    public UserResponse updateAvatar(MultipartFile avatar, Authentication authentication) {
        if (avatar == null || avatar.isEmpty()) {
            throw new CustomException(ResponseCode.VALIDATION_FAILED);
        }

        var user = getCurrentUser(authentication);
        var userInfo = user.getUserInfo();

        try {
            Path rootPath = Paths.get(uploadsPath, "avatars");
            Files.createDirectories(rootPath);

            String fileName = UUID.randomUUID() + getFileExtension(avatar.getOriginalFilename());
            Path destination = rootPath.resolve(fileName).normalize();
            Files.copy(avatar.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            deleteAvatarFile(userInfo.getAvatarUrl());
            userInfo.setAvatarUrl(destination.toString().replace('\\', '/'));
            userInfoRepository.save(userInfo);
            return userMapper.toUserResponse(userInfo);
        } catch (IOException e) {
            throw new CustomException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteAvatar(Authentication authentication) {
        var user = getCurrentUser(authentication);
        var userInfo = user.getUserInfo();
        deleteAvatarFile(userInfo.getAvatarUrl());
        userInfo.setAvatarUrl(null);
        userInfoRepository.save(userInfo);
    }

    @Override
    @Transactional
    public void changeEmail(ChangeEmailRequest request, Authentication authentication) {
        var user = getCurrentUser(authentication);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new CustomException(ResponseCode.INVALID_CURRENT_PASSWORD);
        }

        userRepository.findByEmail(request.getNewEmail())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(user.getId())) {
                        throw new CustomException(ResponseCode.EMAIL_ALREADY_EXISTS);
                    }
                });

        user.getUserInfo().setEmail(request.getNewEmail());
        userInfoRepository.save(user.getUserInfo());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changePhone(ChangePhoneRequest request, Authentication authentication) {
        var user = getCurrentUser(authentication);
        user.getUserInfo().setPhoneNumber(request.getPhoneNumber());
        userInfoRepository.save(user.getUserInfo());
    }

    @Override
    @Transactional
    public void deleteAccount(Authentication authentication) {
        var user = getCurrentUser(authentication);
        user.setDeleted(true);
        user.setEnabled(false);
        user.setLocked(true);
        userRepository.save(user);
    }

    @Override
    public UserDashboardResponse getDashboard(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        String accessToken = authHeader.substring(7);

        String userEmail = jwtUtils.extractUsername(accessToken);
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        var wallet = walletRepository.findByUser_Id(user.getId()).orElse(null);

        return UserDashboardResponse.builder()
                .profile(userMapper.toUserResponse(user.getUserInfo()))
                .totalCarsOwned(carRepository.countByOwner_Id(user.getId()))
                .totalBookingsAsCustomer(bookingRepository.countByCustomer_Id(user.getId()))
                .totalBookingsAsOwner(bookingRepository.countByOwner_Id(user.getId()))
                .totalFavorites(favoriteRepository.countByCustomer_Id(user.getId()))
                .unreadNotifications(notificationRepository.countByUser_IdAndStatus(user.getId(), com.project.rentalcar.common.enums.NotificationStatus.UNREAD))
                .walletBalance(wallet != null ? wallet.getBalance() : BigDecimal.ZERO)
                .build();
    }

    private User getCurrentUser(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new CustomException(ResponseCode.ACCESS_DENIED);
        }

        return userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
    }

    private void deleteAvatarFile(String avatarUrl) {
        if (avatarUrl == null || avatarUrl.isBlank()) {
            return;
        }

        try {
            Files.deleteIfExists(Paths.get(avatarUrl));
        } catch (IOException ignored) {
            // Best effort cleanup for local uploads.
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return ".png";
        }
        return fileName.substring(fileName.lastIndexOf('.')).toLowerCase(Locale.ROOT);
    }

    private String generateRandomPassword() {
        int length = 8;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(character.charAt(random.nextInt(character.length())));
        }
        return sb.toString();
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var validOtp = generateAndActivateCode(user);
        emailService.sendEmail(
                user.getUserInfo().getEmail(),
                user.getUserInfo().getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                validOtp,
                "Activate your account"
        );
    }

    private String generateAndActivateCode(User user) {
        var actCode = generateActivationCode();

        String otpKey = "otp:" + user.getUserInfo().getEmail();
        redisService.setValue(otpKey, actCode, 5, TimeUnit.MINUTES);

        UserOtp otp = new UserOtp();
        otp.setId(UUID.randomUUID().toString());
        otp.setOtpCode(actCode);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(3));
        otp.setIssuedAt(LocalDateTime.now());
        otp.setValidatedAt(null);
        otp.setUsed(false);
        otp.setUser(user);
        userOtpRepository.save(otp);
        return actCode;
    }

    private String generateActivationCode() {
        String character = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(character.length());
            char randomChar = character.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
