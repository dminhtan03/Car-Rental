package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {
    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @NotBlank(message = "Phone Number is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be 6 characters long minimum")
    private String password;

    @NotBlank(message = "Role is mandatory")
    private String role;
}
