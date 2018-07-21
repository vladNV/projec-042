package com.example.app.repository;

import com.example.app.domain.Trip;
import com.example.app.model.BusinessTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM t_trip WHERE trip_code = ?1)", nativeQuery = true)
    Long exists(String rateNumber);

}
