package org.example.eventorganization.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 25)
    String firstName;
    @Column(length = 25)
    String lastName;
    @Column(unique = true,length = 30)
    String username;
    @Column(length = 64)
    String password;
    @Column(length = 35,unique = true)
    String email;
    @Column(length = 15,unique = true)
    String phoneNumber;
    Double wallet;
    @Builder.Default
    Boolean isActive=false;
    @Builder.Default
    Integer countAttempts=3;
    LocalDateTime attemptBlockedDate;

    @OneToOne
    @JoinColumn(name = "photo_id")
    Photo photo;
    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    List<Participant>participants;
    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Token token;
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
    mappedBy = "user")
    OTP otp;
}
