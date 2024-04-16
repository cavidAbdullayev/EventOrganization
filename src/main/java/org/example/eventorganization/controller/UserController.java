package org.example.eventorganization.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.user.CreateUserRequest;
import org.example.eventorganization.dto.request.user.RenewPasswordRequest;
import org.example.eventorganization.dto.request.user.ResetPasswordRequest;
import org.example.eventorganization.dto.response.user.GetUserResponse;
import org.example.eventorganization.dto.response.user.UpdatePersonalInfosRequest;
import org.example.eventorganization.model.User;
import org.example.eventorganization.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;
    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam("token") String token){
        userService.confirmRegistration(token);
    }
    @PostMapping("/registration")
    public CreateUserRequest registration(@Valid @RequestBody CreateUserRequest request){
        return userService.registration(request);
    }
    @GetMapping("/confirm-login")
    public ResponseEntity<String> loginUsingOtp(@RequestParam("otp") @Valid Integer otp){
        return userService.loginUsingOtp(otp);
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") @Valid String username,
                      @RequestParam("password") @Valid String password){
        return userService.login(username,password);
    }
    @PutMapping("/update-username")
    public ResponseEntity<String> updateUsername(@RequestParam("username") @Valid String username){
        return userService.updateUsername(username);
    }
    @PutMapping("/update-personal-infos")
    public GetUserResponse updatePersonalInfos(@RequestBody @Valid UpdatePersonalInfosRequest request){
        return userService.updatePersonalInfos(request);
    }
    @PutMapping("/change-email")
    public ResponseEntity<String>changeEmail(@RequestParam @Valid String email){
       return userService.changeEmail(email);
    }
    @GetMapping("/confirm-email")
    public void confirmEmail(@RequestParam @Valid String token,
                             @RequestParam @Valid String email){
        userService.confirmEmail(token,email);
    }
    @GetMapping("/change-password")
    public void changePassword(@RequestParam @Valid String password,
                               @RequestParam @Valid String token) {
        userService.changePassword(password,token);
    }
    @PutMapping("/renew-password")
    public ResponseEntity<String> renewPassword(@RequestBody @Valid RenewPasswordRequest request){
        return userService.renewPassword(request);
    }
    @PutMapping("/reset-password")
    public ResponseEntity<String>resetPassword(){
        return userService.resetPassword();
    }
    @PutMapping("/reset-password-confirm")
    public void confirmResetPassword(@RequestBody @Valid ResetPasswordRequest request){
        userService.confirmResetPassword(request);
    }
    @PutMapping("/upload-photo")
    public ResponseEntity<byte[]>uploadPhoto(@RequestPart("file") MultipartFile file){
        return userService.uploadPhoto(file);
    }
    @GetMapping("/show-image")
    public ResponseEntity<byte[]>showImage(){
        return userService.showImage();
    }
}
