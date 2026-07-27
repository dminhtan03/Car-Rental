package com.project.rentalcar.service;

import com.project.rentalcar.model.dto.request.ChangePasswordRequest;
import com.project.rentalcar.model.dto.request.ForgotPasswordRequest;
import com.project.rentalcar.model.dto.request.ForgotPasswordVerifyRequest;
import com.project.rentalcar.model.dto.request.RegistrationRequest;
import com.project.rentalcar.model.dto.response.RegistrationResponse;
import com.project.rentalcar.model.dto.response.UserResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    RegistrationResponse register(RegistrationRequest request);

    void changePassword(ChangePasswordRequest request, Authentication authentication);

    void handleForgotPassword(ForgotPasswordRequest request);

    void verifyForgotPassword(ForgotPasswordVerifyRequest request);

    void activateAccount(String validOtp);

    List<UserResponse> getAllUser();
}
