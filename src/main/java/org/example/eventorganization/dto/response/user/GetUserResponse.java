package org.example.eventorganization.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetUserResponse {
    String firstName;
    String lastName;
    String username;
    String email;
    String phoneNumber;
    Double wallet;
}
