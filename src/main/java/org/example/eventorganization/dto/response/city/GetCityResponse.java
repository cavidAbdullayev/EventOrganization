package org.example.eventorganization.dto.response.city;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GetCityResponse {
    String name;
    Integer population;
    Double area;
    Integer establishedYear;
}
