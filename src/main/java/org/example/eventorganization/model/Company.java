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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 25)
    String name;
    @Column(length = 25)
    String owner;
    Double budget;
    Integer totalPersonnel;
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Event>events;
    @OneToOne
    @JoinColumn(name = "company_id")
    Location location;
}
