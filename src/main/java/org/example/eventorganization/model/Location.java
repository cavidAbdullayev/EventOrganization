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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 30,unique = true)
    String address;
    @Column(unique = true,length = 10)
    String postalCode;
    Double budget;
    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
    @OneToOne(mappedBy = "location",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Staff staff;
    @OneToOne(mappedBy = "location",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Event event;
    @OneToOne(mappedBy = "location",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Company company;
    @OneToOne(mappedBy = "location",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Hall hall;
}
