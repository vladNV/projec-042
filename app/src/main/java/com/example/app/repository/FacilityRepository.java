package com.example.app.repository;

import com.example.app.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query(name = "SELECT * FROM t_facility WHERE title LIKE ?1%", nativeQuery = true)
    List<Facility> findByDirection(String direction);

    @Query(name = "SELECT * FROM t_facility WHERE direction LIKE ?1%", nativeQuery = true)
    List<Facility> findByTitle(String title);

}
