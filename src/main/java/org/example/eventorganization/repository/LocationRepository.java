package org.example.eventorganization.repository;

import org.example.eventorganization.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    boolean existsByAddress(String address);
    boolean existsByPostalCode(String postalCode);
}
