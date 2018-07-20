package com.example.app.service.impl;

import com.example.app.converter.BusinessTripConverter;
import com.example.app.domain.*;
import com.example.app.model.BusinessTrip;
import com.example.app.repository.BusinessTripRepository;
import com.example.app.repository.TripRepository;
import com.example.app.service.*;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Transactional
@Component
public class TripServiceImpl implements TripService {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("settings");
    private static final Integer COUNT = Integer.valueOf(resourceBundle.getObject("count_on_page").toString());

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusinessTripRepository bussinesTripRepository;


    @Override
    @Transactional
    public void createNewBusinessTrip(@NonNull BusinessTrip businessTrip) {
        Employee employee = BusinessTripConverter.toEmployee(businessTrip);
        Requisition requisition = BusinessTripConverter.toRequisition(businessTrip);
        Facility facility = BusinessTripConverter.toFacility(businessTrip);
        Transport transport = BusinessTripConverter.toTransport(businessTrip);
        Trip trip = BusinessTripConverter.toTrip(businessTrip);

        requisition.setEmployee(employee);
        facility.setEmployee(employee);

        trip.setEmployee(employee);
        trip.setTransport(transport);

        facilityService.putFacility(facility);
        requisitionService.createNewRequisition(requisition);
        employeeService.createNewEmployee(employee);
        transportService.putTransport(transport);
        createNewTrip(trip);
    }


    @Override
    public void createNewTrip(@NonNull Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public List<BusinessTrip> retrieveAllBusinessTripsBySort() {
        return ListUtils.emptyIfNull(bussinesTripRepository.findAll().stream()
                .map(BusinessTripConverter::toBusinessTrip).collect(Collectors.toList()));
    }

    @Override
    public List<BusinessTrip> retrieveBusinessTrips(@NonNull final Integer pageNumber, @NotEmpty final String filter) {
        return null;
    }

    @Override
    public List<BusinessTrip> retrieveBusinessTrips(@NonNull final Integer pageNubmer) {
        return ListUtils.emptyIfNull(bussinesTripRepository.findAll(new PageRequest(COUNT * (pageNubmer - 1) / COUNT, COUNT))
                .stream().map(BusinessTripConverter::toBusinessTrip).collect(Collectors.toList()));
    }

    @Override
    public Long count() {
        return bussinesTripRepository.count();
    }

}
