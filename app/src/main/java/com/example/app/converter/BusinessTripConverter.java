package com.example.app.converter;

import com.example.app.domain.*;
import com.example.app.domain.view.BusinessTripView;
import com.example.app.model.BusinessTrip;

import java.util.Optional;

/**
 * Converter class, just convertes one object to others
 */
public class BusinessTripConverter {
    public static BusinessTrip setTrip(BusinessTrip businessTrip, Trip trip) {
        businessTrip = Optional.ofNullable(businessTrip).orElse(new BusinessTrip());
        businessTrip.setDepartureDate(trip.getDeparture());
        businessTrip.setRateNumber(trip.getRateNumber());
        businessTrip.setTicketPrice(trip.getTicketPrice());
        return businessTrip;
    }

    public static BusinessTrip setRequisition(BusinessTrip businessTrip, Requisition requisition) {
        businessTrip = Optional.ofNullable(businessTrip).orElse(new BusinessTrip());
        businessTrip.setDescription(requisition.getDescription());
        businessTrip.setFrom(requisition.getFrom());
        businessTrip.setTill(requisition.getTill());
        return businessTrip;
    }

    public static BusinessTrip setEmployee(BusinessTrip businessTrip, Employee employee) {
        businessTrip = Optional.ofNullable(businessTrip).orElse(new BusinessTrip());
        businessTrip.setEmployeeId(employee.getId());
        businessTrip.setName(employee.getFullname());
        businessTrip.setPassportNumber(employee.getPassport());
        businessTrip.setPhone(employee.getPhone());
        businessTrip.setPosition(employee.getPosition());
        businessTrip.setQualification(employee.getQualification());
        return businessTrip;
    }

    public static BusinessTrip setTranposrt(BusinessTrip businessTrip, Transport transport) {
        businessTrip = Optional.ofNullable(businessTrip).orElse(new BusinessTrip());
        businessTrip.setTransportType(transport.getType());
        businessTrip.setTransportTitle(transport.getTitle());
        return businessTrip;
    }

    public static BusinessTrip setFacility(BusinessTrip businessTrip, Facility facility) {
        businessTrip = Optional.ofNullable(businessTrip).orElse(new BusinessTrip());
        businessTrip.setFacilityDirection(facility.getDirection());
        businessTrip.setFacilityTitle(facility.getTitle());
        return businessTrip;
    }

    public static Trip toTrip(BusinessTrip businessTrip) {
        Trip trip = new Trip();
        trip.setDeparture(businessTrip.getDepartureDate());
        trip.setRateNumber(businessTrip.getRateNumber());
        trip.setTicketPrice(businessTrip.getTicketPrice());
        return trip;
    }

    public static Requisition toRequisition(BusinessTrip businessTrip) {
        Requisition requisition = new Requisition();
        requisition.setDescription(businessTrip.getDescription());
        requisition.setFrom(businessTrip.getFrom());
        requisition.setTill(businessTrip.getTill());
        return requisition;
    }

    public static Employee toEmployee(BusinessTrip businessTrip) {
        Employee employee = new Employee();
        employee.setId(businessTrip.getEmployeeId());
        employee.setFullname(businessTrip.getName());
        employee.setPassport(businessTrip.getPassportNumber());
        employee.setPhone(businessTrip.getPhone());
        employee.setPosition(businessTrip.getPosition());
        employee.setQualification(businessTrip.getQualification());
        return employee;
    }

    public static Transport toTransport(BusinessTrip businessTrip) {
        Transport transport = new Transport();
        transport.setTitle(businessTrip.getTransportTitle());
        transport.setType(businessTrip.getTransportType());
        return transport;
    }

    public static Facility toFacility(BusinessTrip businessTrip) {
        Facility facility = new Facility();
        facility.setTitle(businessTrip.getFacilityTitle());
        facility.setDirection(businessTrip.getFacilityDirection());
        return facility;
    }

    public static BusinessTrip toBusinessTrip(BusinessTripView businessTripView) {
        BusinessTrip businessTrip = new BusinessTrip();

        businessTrip.setPrime(Optional.ofNullable(businessTripView.getPrime()).orElse(0L));

        businessTrip.setTransportTitle(businessTripView.getTransportName());
        businessTrip.setTransportType(TransportType.values()[businessTripView.getTransportType()]);

        businessTrip.setFacilityTitle(businessTripView.getTitle());
        businessTrip.setFacilityDirection(businessTripView.getDirection());

        businessTrip.setQualification(businessTripView.getQualification());
        businessTrip.setPosition(Position.values()[businessTripView.getPosition()]);
        businessTrip.setPhone(businessTripView.getPhone());
        businessTrip.setPassportNumber(businessTripView.getPassport());
        businessTrip.setName(businessTripView.getFullname());
        businessTrip.setEmployeeId(businessTripView.getEmployeeId());

        businessTrip.setTill(businessTripView.getTill());
        businessTrip.setFrom(businessTripView.getFrom());

        businessTrip.setDescription(businessTripView.getDescription());

        businessTrip.setRateNumber(businessTripView.getTripCode());
        businessTrip.setTicketPrice(businessTripView.getCost());
        businessTrip.setDepartureDate(businessTripView.getDeparture());

        return businessTrip;
    }
}
