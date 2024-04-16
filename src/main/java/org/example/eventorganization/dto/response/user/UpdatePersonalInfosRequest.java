package org.example.eventorganization.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdatePersonalInfosRequest {
    String firstName;
    String lastName;
    String phoneNumber;
}
