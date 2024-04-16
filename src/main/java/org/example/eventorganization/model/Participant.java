package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne(mappedBy = "participant",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
