package org.example.eventorganization.repository;

import org.example.eventorganization.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Integer> {

}
