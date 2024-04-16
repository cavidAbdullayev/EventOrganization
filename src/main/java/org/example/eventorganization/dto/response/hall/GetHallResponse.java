package org.example.eventorganization.dto.response.hall;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class GetHallResponse {
    Double budget;
    String name;
    String owner;
    Integer capacity;
    Double pricePerHour;
}
