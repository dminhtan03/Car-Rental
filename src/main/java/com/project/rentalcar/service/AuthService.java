package com.project.rentalcar.service;

import com.project.rentalcar.model.dto.request.LoginRequest;
import com.project.rentalcar.model.dto.response.AuthResponse;
import com.project.rentalcar.model.dto.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse doLogin(LoginRequest request, HttpServletResponse response);

    void doLogout(HttpServletRequest request);

    AuthResponse refreshToken(String refreshToken, HttpServletResponse response);

    UserResponse getProfile(HttpServletRequest request);
}
