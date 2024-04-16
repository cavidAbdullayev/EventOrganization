package org.example.eventorganization.dto.request.city;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateCityRequest {
    @Size(max = 25,message = "City name length cannot be greater than 25!")
    String name;
    @Min(value = 0,message = "Area must be equal or greater than 0!")
    Integer population;
    @Min(value = 1,message = "Area must be equal or greater than 1!")
    Double area;
    @Max(value = 2024,message = "Established year cannot be greater than 2024!")
    Integer establishedYear;
}
