package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 50)
    String title;
    @Lob
    String description;
    Integer seatCount;

    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Sponsor>sponsors;
    @OneToOne(mappedBy = "event",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Schedule schedule;
    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
    @OneToOne
    @JoinColumn(name = "location_id")
    Location location;
    @OneToOne
    @JoinColumn(name = "room_id")
    Room room;
    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Ticket>tickets;
}
