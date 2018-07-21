package com.example.app.service.impl;

import com.example.app.converter.BusinessTripConverter;
import com.example.app.domain.Employee;
import com.example.app.domain.Facility;
import com.example.app.domain.Requisition;
import com.example.app.domain.Transport;
import com.example.app.domain.Trip;
import com.example.app.model.BusinessTrip;
import com.example.app.repository.BusinessTripRepository;
import com.example.app.repository.TripRepository;
import com.example.app.service.EmployeeService;
import com.example.app.service.FacilityService;
import com.example.app.service.RequisitionService;
import com.example.app.service.TransportService;
import com.example.app.service.TripService;
import com.example.app.service.exception.UniquieException;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Employee employee = getStoredEmployeeOrElseNew(BusinessTripConverter.toEmployee(businessTrip));
        Facility facility = getStoredFacility(BusinessTripConverter.toFacility(businessTrip));

        saveRelationshipWithFacility(facility, employee);
        saveRelationshipWithRequestion(BusinessTripConverter.toRequisition(businessTrip), employee);

        Trip trip = BusinessTripConverter.toTrip(businessTrip);
        saveRelationShipWithTrip(trip, employee);
        takeTransport(BusinessTripConverter.toTransport(businessTrip), trip);

        createNewTrip(trip);
    }

    private Employee getStoredEmployeeOrElseNew(Employee employee) {
        return Optional.ofNullable(employeeService.getByPassport(employee.getPassport())).orElse(employee);
    }

    private Facility getStoredFacility(Facility facility) {
        return Optional.ofNullable(facilityService.getStoredFacility(facility.getDirection(), facility.getTitle())).orElse(facility);
    }

    private void checkRateNumberForUniqueness(String rateNumber) {
        if (tripRepository.exists(rateNumber) == 1) {
            throw new UniquieException("Номер #" + rateNumber + " уже существует !");
        }
    }

    private void saveRelationshipWithFacility(Facility facility, Employee employee) {
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        facility.setEmployees(list);
        facilityService.putFacility(facility);
    }

    private void saveRelationshipWithRequestion(Requisition requisition, Employee employee) {
        requisition.setEmployee(employee);
        requisitionService.createNewRequisition(requisition);
    }

    private void takeTransport(Transport transport, Trip trip) {
        trip.setTransport(transport);
        transportService.putTransport(transport);
    }

    private void saveRelationShipWithTrip(Trip trip, Employee employee) {
        checkRateNumberForUniqueness(trip.getRateNumber());
        trip.setEmployee(employee);
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
        return ListUtils.emptyIfNull(bussinesTripRepository.findAll(new PageRequest(COUNT *
                (pageNubmer - 1) / COUNT, COUNT))
                .stream().map(BusinessTripConverter::toBusinessTrip).collect(Collectors.toList()));
    }

    @Override
    public Long count() {
        return bussinesTripRepository.count();
    }

}
