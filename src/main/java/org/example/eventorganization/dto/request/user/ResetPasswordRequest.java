package org.example.eventorganization.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ResetPasswordRequest {
    @NotEmpty(message = "OTP is required!")
    Integer otp;
    @Size(max = 64,min = 8,message = "Password length must be between 8 and 64!")
    @NotEmpty(message = "New password is required!")
    String newPassword;
    @NotEmpty(message = "Repeat password is required!")
    @Size(max = 64,min = 8,message = "Password length must be between 8 and 64!")
    String repeatPassword;
}
