package org.example.eventorganization.dto.request.room;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateRoomRequest {
    @Size(max = 10,message = "Room number length cannot be greater than 10!")
    String roomNumber;
    @Max(value = 1000,message = "Room capacity cannot be greater than 1000!")
    Integer capacity;
}
