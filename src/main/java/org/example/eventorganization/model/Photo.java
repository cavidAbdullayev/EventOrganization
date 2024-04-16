package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 25)
    String name;
    Double size;
    @CreationTimestamp
    LocalDateTime uploadedDate;
    @OneToOne(mappedBy = "photo",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    User user;
}
