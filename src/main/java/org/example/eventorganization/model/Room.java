package org.example.eventorganization.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 10,unique = true)
    String roomNumber;
    @Column
    @Max(value = 1000)
    Integer capacity;
    @OneToOne(mappedBy = "room",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Event event;
}