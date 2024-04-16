package org.example.eventorganization.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.user.CreateUserRequest;
import org.example.eventorganization.dto.response.user.GetUserResponse;
import org.example.eventorganization.dto.response.user.UpdatePersonalInfosRequest;
import org.example.eventorganization.mapper.UserMapper;
import org.example.eventorganization.model.User;
import org.example.eventorganization.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserMapperImpl implements UserMapper {
UserRepository userRepository;
    @Override
    public GetUserResponse mapToResponse(User user) {
        return GetUserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .wallet(user.getWallet())
                .username(user.getUsername())
                .build();
    }

    @Override
    public User mapToUser(CreateUserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .wallet(request.getWallet())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    @Override
    public void mapForUpdatePersonalInfos(User user, UpdatePersonalInfosRequest request) {
        if(request.getFirstName()!=null)
            user.setFirstName(request.getFirstName());
        if(request.getLastName()!=null)
            user.setLastName(request.getLastName());
        if(request.getPhoneNumber()!=null)
            user.setPhoneNumber(request.getPhoneNumber());
    }

}
