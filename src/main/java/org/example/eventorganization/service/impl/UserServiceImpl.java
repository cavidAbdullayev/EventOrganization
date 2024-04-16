package org.example.eventorganization.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.request.user.CreateUserRequest;
import org.example.eventorganization.dto.request.user.RenewPasswordRequest;
import org.example.eventorganization.dto.request.user.ResetPasswordRequest;
import org.example.eventorganization.dto.response.user.GetUserResponse;
import org.example.eventorganization.dto.response.user.UpdatePersonalInfosRequest;
import org.example.eventorganization.mapper.UserMapper;
import org.example.eventorganization.model.OTP;
import org.example.eventorganization.model.Photo;
import org.example.eventorganization.model.Token;
import org.example.eventorganization.model.User;
import org.example.eventorganization.repository.OtpRepository;
import org.example.eventorganization.repository.PhotoRepository;
import org.example.eventorganization.repository.TokenRepository;
import org.example.eventorganization.repository.UserRepository;
import org.example.eventorganization.service.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static org.example.eventorganization.global.GlobalData.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    JavaMailSender mailSender;
    TokenRepository tokenRepository;
    OtpRepository otpRepository;
    PhotoRepository photoRepository;
    @Override
    public CreateUserRequest registration(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User given by email already exists!");
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new RuntimeException("User given by phone number already exists!");
        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User given by email already exists!");
        User user=userMapper.mapToUser(request);
        String token= UUID.randomUUID().toString();
        Token confirmToken=Token.builder()
                .token(token)
                .user(user)
                .build();
        String url="http://localhost:8080/api/v1/user/confirm-registration?token="+token;
        SimpleMailMessage message=new SimpleMailMessage();
        message.setText("Please, follow the link to confirm your registration:\n"+url);
        message.setSubject("Confirm registration");
        message.setFrom("cavidabdullayevv20@gmail.com");
        message.setTo(user.getEmail());
        mailSender.send(message);
        userRepository.save(user);
        tokenRepository.save(confirmToken);
        return request;
    }

    @Override
    public void confirmRegistration(String token) {
        Token confirmationToken=tokenRepository.findTokenByToken(token).orElseThrow(()->
                new RuntimeException("User not found!"));
        User user=confirmationToken.getUser();
        user.setIsActive(true);
        userRepository.save(user);
        currentUserId=user.getId();
    }

    @Override
    public ResponseEntity<String> login(String username, String password) {
        User user=userRepository.findByUsername(username).orElseThrow(()->
                new RuntimeException("User given by username not found!"));
        if(!password.equals(user.getPassword())&&user.getCountAttempts()>1){
            user.setCountAttempts(user.getCountAttempts()-1);
            userRepository.save(user);
            throw new RuntimeException(user.getCountAttempts()==1? "Password is not correct! You have "
                    +user.getCountAttempts()+" attempt":"Password is not correct! You have "
                    +user.getCountAttempts()+" attempts");
        }
        else if(user.getCountAttempts()==1&&(user.getAttemptBlockedDate()==null||
                ChronoUnit.MINUTES.between(user.getAttemptBlockedDate(),LocalDateTime.now())>=60)){
            user.setAttemptBlockedDate(LocalDateTime.now());
            userRepository.save(user);
            throw new RuntimeException("You tried too many for logging! Please, wait 60 minutes for logging again!");
        }
        else if(user.getCountAttempts()==1&&ChronoUnit.MINUTES.between(user.getAttemptBlockedDate(),LocalDateTime.now())<60){
            long minutes=ChronoUnit.MINUTES.between(user
                    .getAttemptBlockedDate(),LocalDateTime.now());
            if(minutes!=1)
                throw new RuntimeException("You tried too many for logging! Please, wait "
                        +(60-minutes)+" minutes for logging again!");
            else
                throw new RuntimeException("You tried too many for logging! Please, wait "
                        +(60-minutes)+" minute for logging again!");
        }
        if(password.equals(user.getPassword())){
            Random random=new Random();
            int otp=random.nextInt(1000,9999);
            OTP confirmationOtp;
            if(otpRepository.findByUser_Id(user.getId()).isPresent()){
                confirmationOtp=otpRepository.findByUser_Id(user.getId()).get();
                confirmationOtp.setOtp(otp);
                otpRepository.save(confirmationOtp);
            }else{
                confirmationOtp=OTP.builder()
                        .user(user)
                        .otp(otp)
                        .expireDate(LocalDateTime.now())
                        .build();
                otpRepository.save(confirmationOtp);
            }
            otpRepository.save(confirmationOtp);
            SimpleMailMessage message=new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setFrom("cavidabdullayevv020@gmail.com");
            message.setSubject("Login confirmation");
            message.setText("Your OTP code for login:\n"+otp);
            mailSender.send(message);
            userIdLastAttemptedForLogin=user.getId();
        }
        return ResponseEntity.ok("OTP was sent successfully!");
    }
    @Override
    public ResponseEntity<String> loginUsingOtp(Integer otp){
        User user=userRepository.getReferenceById(userIdLastAttemptedForLogin);
        OTP confirmationOtp=otpRepository.findByUser_Id(userIdLastAttemptedForLogin).get();
        if (ChronoUnit.MINUTES.between(confirmationOtp.getExpireDate(),LocalDateTime.now())>=5)
            throw new RuntimeException("OTP already expired!");
        if(!Objects.equals(confirmationOtp.getOtp(), otp))
            throw new RuntimeException("Given OTP is incorrect!");
        else
            currentUserId=user.getId();
        return ResponseEntity.ok("You are logged successfully!");
    }
    @Override
    public GetUserResponse getById(Integer id) {
        return userMapper.mapToResponse(userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User given by id not found!")));
    }

    @Override
    public ResponseEntity<String>updateUsername(String newUsername) {
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        User user=userRepository.getReferenceById(currentUserId);
        if(userRepository.existsByEmail(newUsername))
            throw new RuntimeException("This username already is used!");
        user.setUsername(newUsername);
        userRepository.save(user);
        return ResponseEntity.ok("Username updated successfully!");
    }

    @Override
    public GetUserResponse updatePersonalInfos(UpdatePersonalInfosRequest request) {
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        User user=userRepository.getReferenceById(currentUserId);
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new RuntimeException("User given by phone number already exists!");
        userMapper.mapForUpdatePersonalInfos(user,request);
        userRepository.save(user);
        return userMapper.mapToResponse(user);
    }
    @Override
    public ResponseEntity<String>changeEmail(String email){
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        if(userRepository.existsByEmail(email))
            throw new RuntimeException("User given by email already exists!");
        Token confirmationToken=tokenRepository.findTokenByUser_Id(currentUserId);
        String token=UUID.randomUUID().toString();
        confirmationToken.setToken(token);
        tokenRepository.save(confirmationToken);
        String url="http://localhost:8080/api/v1/user/confirm-email?token="+token
                +"&email="+email;
        SimpleMailMessage message=new SimpleMailMessage();
        message.setText("Please, follow the link confirm your new email:\n"+url);
        message.setFrom("cavidabdullayevv20@gmail.com");
        message.setTo(email);
        message.setSubject("Confirm email");
        mailSender.send(message);
        return ResponseEntity.ok("Message for confirmation was sent successfully!");
    }
    @Override
    public void confirmEmail(String token,String email) {
        System.out.println(token);
        Token confirmationToken=tokenRepository.findTokenByToken(token).orElseThrow(()->
                new RuntimeException("User not found!"));
        User user=confirmationToken.getUser();
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<String> renewPassword(RenewPasswordRequest request) {
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        User user=userRepository.getReferenceById(currentUserId);
        if(request.getOldPassword().equals(user.getPassword())){
            if(request.getNewPassword().equals(request.getRepeatPassword())){
                Token confirmationToken=tokenRepository.findTokenByUser_Id(currentUserId);
                confirmationToken.setToken(UUID.randomUUID().toString());
                tokenRepository.save(confirmationToken);
                String url="http://localhost:8080/api/v1/user/change-password?password="+
                        request.getNewPassword()+"&token="+confirmationToken.getToken();
                SimpleMailMessage message=new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject("Renew Password");
                message.setFrom("cavidabdullayevv20@gmail.com");
                message.setText("Please, follow the link to renew your password:\n"+url);
                mailSender.send(message);
            }
            else
                throw new RuntimeException("New password and it's repeat don't matching!");
        }else
            throw new RuntimeException("Old password is not correct!");
        return ResponseEntity.ok("Message for renew password was sent successfully!");
    }

    @Override
    public void changePassword(String password, String token) {
        User user=tokenRepository.findTokenByToken(token).orElseThrow(()->
                new RuntimeException("User not found!")).getUser();
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<String>resetPassword(){
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        User user=userRepository.getReferenceById(currentUserId);
        Random random=new Random();
        int otp=random.nextInt(1000,9999);
        OTP confirmationOtp;
        if(otpRepository.findByUser_Id(currentUserId).isPresent()){
            confirmationOtp=otpRepository.findByUser_Id(currentUserId).get();
            confirmationOtp.setOtp(otp);
            confirmationOtp.setExpireDate(LocalDateTime.now().plusMinutes(5));
        }else {
            confirmationOtp=OTP.builder()
                    .otp(otp)
                    .user(user)
                    .expireDate(LocalDateTime.now().plusMinutes(5))
                    .build();
        }
        otpRepository.save(confirmationOtp);
        SimpleMailMessage message=new SimpleMailMessage();
        message.setText("Your OTP code for reset password:\n"+otp);
        message.setFrom("cavidabdullayevv20@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Reset password");
        mailSender.send(message);
        return ResponseEntity.ok("OTP was sent successfully!");
    }

    @Override
    public void confirmResetPassword(ResetPasswordRequest request){
        OTP confirmationOtp;
        User user=userRepository.getReferenceById(currentUserId);
            confirmationOtp=otpRepository.findByUser_Id(currentUserId).get();
            if(ChronoUnit.MINUTES.between(LocalDateTime.now(),confirmationOtp.getExpireDate())<=0)
                throw new RuntimeException("OTP already was expired!");
            else if(request.getOtp().equals(confirmationOtp.getOtp())){
                if(request.getNewPassword().equals(request.getRepeatPassword())){
                    user.setPassword(request.getNewPassword());
                    userRepository.save(user);
                }else
                    throw new RuntimeException("New password and it's repeat don't matching!");
            }else
                throw new RuntimeException("OTP is not correct!");
    }

    @SneakyThrows
    @Override
    public ResponseEntity<byte[]> uploadPhoto(MultipartFile file) {
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        User user=userRepository.getReferenceById(currentUserId);
        byte[] bytes=file.getInputStream().readAllBytes();
        String[] fileNameParts= Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension=fileNameParts[fileNameParts.length-1];
        String newFileName= user.getUsername()+"-"+currentUserId+"."+extension;
        String newFilePath="C:\\cavid\\Java Projects\\EventOrganization\\src\\main\\resources\\static\\photos\\"+ newFileName;
        Photo photo;
        if(user.getPhoto()==null){
            try (FileOutputStream outputStream = new FileOutputStream(newFilePath)) {
            outputStream.write(bytes);
            }
             photo=Photo.builder()
                    .name(newFileName)
                    .size((double)(bytes.length/1024))
                    .user(user)
                    .uploadedDate(LocalDateTime.now())
                    .build();
            user.setPhoto(photo);
            photoRepository.save(photo);
            userRepository.save(user);
        }
        else {
             photo=user.getPhoto();
            try(FileOutputStream fileOutputStream=new FileOutputStream("C:\\cavid\\Java Projects\\EventOrganization\\src\\main\\resources\\static\\photos\\"+photo.getName())) {
                fileOutputStream.write(bytes);
            }
            photo.setName(newFileName);
            photo.setSize((double)bytes.length/1024);
            photo.setUploadedDate(LocalDateTime.now());
            photoRepository.save(photo);
        }
        HttpHeaders headers=new HttpHeaders();
        MediaType mediaType=switch (extension){
            case "jpeg","jpg"->MediaType.IMAGE_JPEG;
            case "png"->MediaType.IMAGE_PNG;
            default -> throw new RuntimeException("File format is not supported!");
        };
        headers.setContentType(mediaType);
        System.out.println("file name: "+newFileName);
        System.out.println("photo name: "+photo.getName());
        return new ResponseEntity<>(bytes,headers, HttpStatus.OK);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<byte[]> showImage() {
        if(currentUserId==null)
            throw new RuntimeException("You have not registered yet!");
        User user=userRepository.getReferenceById(currentUserId);
        if(user.getPhoto()==null)
            throw new RuntimeException("You have not upload any photo!");
        else {
            Photo photo=user.getPhoto();
            String fileName="static\\photos\\"+photo.getName();
            byte[]file=new ClassPathResource(fileName)
                    .getInputStream()
                    .readAllBytes();
            HttpHeaders headers=new HttpHeaders();
            String extension=photo.getName().split("\\.")[photo.getName().split("\\.").length-1];
            MediaType mediaType=switch (extension){
                case "jpeg","jpg"->MediaType.IMAGE_JPEG;
                case "png"->MediaType.IMAGE_PNG;
                default -> throw new RuntimeException("File format is not supported!");
            };
            headers.setContentType(mediaType);
            return new ResponseEntity<>(file,headers,HttpStatus.OK);
        }
    }
}