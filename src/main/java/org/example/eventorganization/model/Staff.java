package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 25)
    String firstName;
    @Column(length = 25)
    String lastName;
    @Column(length = 25)
    String position;
    Double salary;
    @CreationTimestamp
    LocalDateTime startDate;

    @OneToOne
    @JoinColumn(name = "location_id")
    Location location;
}