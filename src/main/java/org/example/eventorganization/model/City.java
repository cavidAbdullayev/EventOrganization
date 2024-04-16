package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 25)
    String name;
    Integer population;
    Double area;
    Integer establishedYear;
    @OneToMany(mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Location>locations;
}