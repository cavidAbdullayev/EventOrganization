package org.example.eventorganization.repository;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.example.eventorganization.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
    boolean existsById(@NonNull Integer integer);
}
