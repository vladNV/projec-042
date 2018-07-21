package com.example.app.service.impl;

import com.example.app.domain.Facility;
import com.example.app.repository.FacilityRepository;
import com.example.app.service.FacilityService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public void putFacility(@NonNull Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public List<Facility> findFacilitiesByTitle(String title) {
        return ListUtils.emptyIfNull(facilityRepository.findByTitleIgnoreCaseContaining(title));
    }

    @Override
    public List<Facility> findFacilitiesByDirection(String direction) {
        return ListUtils.emptyIfNull(facilityRepository.findByDirectionIgnoreCaseContaining(direction));
    }

    @Override
    public Facility getStoredFacility(String direction, String title) {
        return facilityRepository.findByDirectionAndTitle(direction, title);
    }

}
