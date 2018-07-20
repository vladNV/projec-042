package com.example.app.repository;

import com.example.app.domain.view.BusinessTripView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTripRepository extends JpaRepository<BusinessTripView, Long> { }
