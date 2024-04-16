package org.example.eventorganization.dto.response.room;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetRoomResponse {
    String roomNumber;
    Integer capacity;
}
