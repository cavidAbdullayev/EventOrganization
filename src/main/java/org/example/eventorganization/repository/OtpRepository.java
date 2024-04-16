package org.example.eventorganization.repository;

import org.example.eventorganization.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OTP,Integer> {
    Optional<OTP>findByUser_Id(Integer userId);
    boolean existsByUser_Id(Integer userId);
}
