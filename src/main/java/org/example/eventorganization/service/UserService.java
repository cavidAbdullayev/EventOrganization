package org.example.eventorganization.service;

import org.example.eventorganization.dto.request.user.CreateUserRequest;
import org.example.eventorganization.dto.request.user.RenewPasswordRequest;
import org.example.eventorganization.dto.request.user.ResetPasswordRequest;
import org.example.eventorganization.dto.response.user.GetUserResponse;
import org.example.eventorganization.dto.response.user.UpdatePersonalInfosRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    CreateUserRequest registration(CreateUserRequest request);
    void confirmRegistration(String token);
    ResponseEntity<String> loginUsingOtp(Integer otp);
    GetUserResponse getById(Integer id);
    ResponseEntity<String> updateUsername(String username);
    GetUserResponse updatePersonalInfos(UpdatePersonalInfosRequest request);
    ResponseEntity<String> renewPassword(RenewPasswordRequest request);
    void changePassword(String password,String token);
    ResponseEntity<String>resetPassword();
    void confirmResetPassword(ResetPasswordRequest request);
    ResponseEntity<String> login(String username,String password);
     ResponseEntity<String>changeEmail(String email);
     void confirmEmail(String token,String email);
     ResponseEntity<byte[]> uploadPhoto(MultipartFile file);
     ResponseEntity<byte[]>showImage();
}