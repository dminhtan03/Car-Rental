package com.HN25_CPL_PJB_01_G4.com.car_rental.Service;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request.LoginRequest;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse doLogin(LoginRequest request, HttpServletResponse response);

    void doLogout(HttpServletRequest request);

    AuthResponse refreshToken(String refreshToken, HttpServletResponse response);

}
