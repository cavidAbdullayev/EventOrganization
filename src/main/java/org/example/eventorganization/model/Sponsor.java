package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 25)
    String name;
    @Lob
    String contractInformation;
    Double sponsorshipLevel;
    @CreationTimestamp
    LocalDate contractDate;
    Double price;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;

}
