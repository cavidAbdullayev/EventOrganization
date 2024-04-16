package org.example.eventorganization.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 15)
    String ticketType;
    Double price;
    LocalDateTime purchaseDate;
    Integer seatNumber;
    @Column(length = 50)
    String participantFullName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;
    @OneToOne
    @JoinColumn(name = "participant_id")
    Participant participant;
}