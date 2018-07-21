package com.example.app.repository;

import com.example.app.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    List<Facility> findByDirectionIgnoreCaseContaining(String direction);

    List<Facility> findByTitleIgnoreCaseContaining(String title);

    Facility findByDirectionAndTitle(String direction, String title);

}
