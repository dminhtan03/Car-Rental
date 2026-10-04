package com.project.rentalcar.mapper;

import com.project.rentalcar.model.dto.request.RegistrationRequest;
import com.project.rentalcar.model.dto.response.RegistrationResponse;
import com.project.rentalcar.model.dto.response.UserResponse;
import com.project.rentalcar.model.entity.User;
import com.project.rentalcar.model.entity.UserInfo;
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
    @Mapping(target = "userInfo", source = "registrationRequest")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "refreshTokens", ignore = true)
    User toUser(RegistrationRequest registrationRequest);

    RegistrationResponse toRegistrationResponse(UserInfo userInfo);

    @Mapping(target = "avatarUrl", source = "avatarUrl")
    UserResponse toUserResponse(UserInfo userInfo);
}
