package com.project.rentalcar.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {
    private UserResponse profile;
    private boolean enabled;
    private boolean locked;
    private int loginCount;
    private int status;
}