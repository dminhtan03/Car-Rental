package com.HN25_CPL_PJB_01_G4.com.car_rental.mapper;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Request.RegistrationRequest;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Response.RegistrationResponse;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Response.UserResponse;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.User;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserInfo toUserInfo(RegistrationRequest registrationRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "enabled", constant = "false")
    @Mapping(target = "loginCount", constant = "0")
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "deleted", constant = "false")
    @Mapping(target = "status", constant = "1")
    @Mapping(target = "userInfo", source = "registrationRequest")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "refreshTokens", ignore = true)
    User toUser(RegistrationRequest registrationRequest);

    RegistrationResponse toRegistrationResponse(UserInfo userInfo);

    UserResponse toUserResponse(UserInfo userInfo);
}
