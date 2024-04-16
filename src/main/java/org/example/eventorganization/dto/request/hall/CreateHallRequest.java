package org.example.eventorganization.dto.request.hall;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class CreateHallRequest {
    @NotNull(message = "Budget is required!")
    @Min(value = 1,message = "Amount of money cannot be less than 1!")
    Double budget;
    @NotBlank(message = "Name is required!")
    @Size(max = 25,message = "Name length cannot be greater than 25!")
    String name;
    @NotBlank(message = "Owner is required!")
    @Size(max = 25,message = "Owner length cannot be greater than 25!")
    String owner;
    @NotNull(message = "Capacity is required!")
    @Min(value = 10,message = "Capacity cannot be less than 1!")
    Integer capacity;
    @NotNull(message = "Price per hour is required!")
    @Min(value = 0,message = "Price per hour cannot be less than 0!")
    Double pricePerHour;
}
