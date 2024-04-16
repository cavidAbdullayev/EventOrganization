package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Double budget;
    @Column(length = 25)
    String name;
    @Column(length = 25)
    String owner;
    Double pricePerHour;

    @OneToOne
    @JoinColumn(name = "location_id")
    Location location;

}