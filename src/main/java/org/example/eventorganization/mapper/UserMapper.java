package org.example.eventorganization.mapper;

import org.example.eventorganization.dto.request.user.CreateUserRequest;
import org.example.eventorganization.dto.response.user.GetUserResponse;
import org.example.eventorganization.dto.response.user.UpdatePersonalInfosRequest;
import org.example.eventorganization.model.User;

public interface UserMapper {
    GetUserResponse mapToResponse(User user);
    User mapToUser(CreateUserRequest request);
    void mapForUpdatePersonalInfos(User user, UpdatePersonalInfosRequest request);
}
