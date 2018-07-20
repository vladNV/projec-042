package com.example.app.service;

import com.example.app.domain.Trip;
import com.example.app.model.BusinessTrip;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {

    void createNewBusinessTrip(BusinessTrip businessTrip);

    void createNewTrip(Trip trip);

    List<BusinessTrip> retrieveAllBusinessTripsBySort();

    List<BusinessTrip> retrieveBusinessTrips(Integer pageNumber, String filter);

    List<BusinessTrip> retrieveBusinessTrips(Integer pageNubmer);

    Long count();


}
