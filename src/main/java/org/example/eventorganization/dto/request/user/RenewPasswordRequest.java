package org.example.eventorganization.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RenewPasswordRequest {
    @NotEmpty(message = "Old password is required!")
    String oldPassword;
    @NotEmpty(message = "New password is required!")
    @Size(max = 64,min = 8,message = "Password length must be between 8 and 64!")
    String newPassword;
    @NotEmpty(message = "Repeat password is required!")
    @Size(max = 64,min = 8,message = "Password length must be between 8 and 64!")
    @Pattern(regexp = "^[A-Za-z0-9]{8,}")
    String repeatPassword;
}
