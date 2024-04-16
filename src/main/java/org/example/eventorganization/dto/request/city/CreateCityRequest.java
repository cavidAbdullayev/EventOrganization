package org.example.eventorganization.dto.request.city;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateCityRequest {
    @Size(max = 25,message = "City name length cannot be grater than 25!")
    @NotEmpty(message = "Name is required!")
    String name;
    @NotNull(message = "Population is required!")
    @Min(value = 0,message = "Population must be equal or greater than 0!")
    Integer population;
    @NotNull(message = "Area is required!")
    @Min(value = 0,message = "Area must be greater than 0!")
    Double area;
    @NotNull(message = "Established year is required!")
    @Max(value = 2024,message = "Year cannot be greater than 2024!")
    Integer establishedYear;
}
