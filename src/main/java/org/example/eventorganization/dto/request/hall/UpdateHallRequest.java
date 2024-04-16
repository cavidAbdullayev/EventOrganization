package org.example.eventorganization.dto.request.hall;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class UpdateHallRequest {
    @Min(value = 1,message = "Amount of money cannot be less than 1!")
    Double budget;
    @Size(max = 25,message = "Name length cannot be greater than 25!")
    String name;
    @Size(max = 25,message = "Owner length cannot be greater than 25!")
    String owner;
    @Min(value = 10,message = "Capacity cannot be less than 1!")
    Integer capacity;
    @Min(value = 0,message = "Price per hour cannot be less than 0!")
    Double pricePerHour;
}
