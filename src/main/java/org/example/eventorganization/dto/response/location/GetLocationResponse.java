package org.example.eventorganization.dto.response.location;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.eventorganization.dto.response.city.GetCityResponse;
import org.example.eventorganization.model.City;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GetLocationResponse {
    String address;
    String postalCode;
    Double budget;
    GetCityResponse cityResponse;
}
