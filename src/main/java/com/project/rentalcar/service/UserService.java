package com.project.rentalcar.service;

import com.project.rentalcar.model.dto.request.ChangePasswordRequest;
import com.project.rentalcar.model.dto.request.ChangeEmailRequest;
import com.project.rentalcar.model.dto.request.ChangePhoneRequest;
import com.project.rentalcar.model.dto.request.ForgotPasswordRequest;
import com.project.rentalcar.model.dto.request.ForgotPasswordVerifyRequest;
import com.project.rentalcar.model.dto.request.RegistrationRequest;
import com.project.rentalcar.model.dto.request.UserProfileUpdateRequest;
import com.project.rentalcar.model.dto.response.RegistrationResponse;
import com.project.rentalcar.model.dto.response.UserDashboardResponse;
import com.project.rentalcar.model.dto.response.UserDetailResponse;
import com.project.rentalcar.model.dto.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    RegistrationResponse register(RegistrationRequest request);

    void changePassword(ChangePasswordRequest request, Authentication authentication);

    void handleForgotPassword(ForgotPasswordRequest request);

    void verifyForgotPassword(ForgotPasswordVerifyRequest request);

    void activateAccount(String validOtp);

    List<UserResponse> getAllUser();

    UserResponse updateProfile(UserProfileUpdateRequest request, Authentication authentication);

    UserDetailResponse getUserById(String id);

    UserResponse updateAvatar(MultipartFile avatar, Authentication authentication);

    void deleteAvatar(Authentication authentication);

    void changeEmail(ChangeEmailRequest request, Authentication authentication);

    void changePhone(ChangePhoneRequest request, Authentication authentication);

    void deleteAccount(Authentication authentication);

    UserDashboardResponse getDashboard(HttpServletRequest request);
}
