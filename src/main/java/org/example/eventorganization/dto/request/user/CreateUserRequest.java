package org.example.eventorganization.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @Size(max = 25,message = "Firstname length cannot be grater than 25!")
    @NotEmpty(message = "First name is required!")
    String firstName;
    @Size(max = 25,message = "Lastname length cannot be grater than 25!")
    @NotEmpty(message = "Last name is required!")
    String lastName;
    @Size(min = 8,max = 64,message = "Password length must be between 8 and 64!")
    @NotEmpty(message = "Password is required!")
    @Pattern(regexp = "^[A-Za-z0-9]{8,}")
    String password;
    @Size(max = 30,message = "Username length cannot be grater than 30!")
    @NotEmpty(message = "Username is required!")
    String username;
    @Size(max = 35,message = "Email length cannot be grater than 35!")
    @NotEmpty(message = "Email is required!")
    String email;
    @Size(max = 15,message = "Phone number length cannot be grater than 15!")
    @NotEmpty(message = "Phone number is required!")
    String phoneNumber;
    @NotNull(message = "Wallet is required!")
    Double wallet;
}
