package com.project.rentalcar.model.dto.response;

import lombok.*;

import java.util.UUID;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    private String department;

    private String email;

    private String gender;
}
