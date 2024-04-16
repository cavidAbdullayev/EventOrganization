package org.example.eventorganization.dto.request.room;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateRoomRequest {
    @Size(max = 10,message = "Room number length cannot be greater than 10!")
    @NotNull(message = "Room number is required!")
    String roomNumber;
    @NotNull(message = "Room capacity is required!")
    @Max(value = 1000,message = "Room capacity cannot be greater than 1000!")
    Integer capacity;
}
