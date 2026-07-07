package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequest {
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
}
