package com.example.app.service;

import com.example.app.domain.Facility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacilityService {

    void putFacility(Facility facility);

    List<Facility> findFacilitiesByTitle(String title);

    List<Facility> findFacilitiesByDirection(String direction);

    Facility getStoredFacility(String direction, String title);

}
