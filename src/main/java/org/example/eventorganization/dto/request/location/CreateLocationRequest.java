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
public class CreateLocationRequest {
    @Size(max = 30,message = "Address length cannot be greater than 30!")
    @NotBlank(message = "Address is required!")
    String address;
    @Size(max = 10,message = "Address length cannot be greater than 10!")
    @NotBlank(message = "Postal code is required!")
    String postalCode;
    @NotNull(message = "Budget is required!")
    @Min(value = 0,message = "Amount of money must be greater than 0!")
    Double budget;
    @NotNull(message = "City ID is required!")
    @Min(value = 0,message = "City ID must be greater than 0!")
    Integer cityId;
}
