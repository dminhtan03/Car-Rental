package com.HN25_CPL_PJB_01_G4.com.car_rental.Service;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request.ChangePasswordRequest;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request.ForgotPasswordRequest;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request.ForgotPasswordVerifyRequest;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request.RegistrationRequest;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Response.RegistrationResponse;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Response.UserResponse;
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
