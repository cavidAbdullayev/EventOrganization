package org.example.eventorganization.dto.request.location;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLocationRequest {
    @Size(max = 30,message = "Address length cannot be greater than 30!")
    String address;
    @Size(max = 10,message = "Address length cannot be greater than 10!")
    String postalCode;
    @Min(value = 0,message = "Amount of money must be greater than 0!")
    Double budget;
    @Min(value = 0,message = "City ID must be greater than 0!")
    Integer cityId;
}
