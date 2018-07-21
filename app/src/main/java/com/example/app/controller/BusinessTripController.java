package com.example.app.controller;

import com.example.app.model.BusinessTrip;
import com.example.app.service.TripService;
import com.example.app.service.exception.UniquieException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class BusinessTripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/business")
    public String getBusinessTripPage() {
        return UI.BUSINESS_TRIPS;
    }

    @GetMapping(value = "/business/trip/{number}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<BusinessTrip>> showAll(@PathVariable(name = "number") String pageNumber) {
        if (StringUtils.isBlank(pageNumber) || !pageNumber.matches(Regex.NUMBER.pattern())) {
            return Result.response(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }

        return Result.response(tripService.retrieveBusinessTrips(Integer.valueOf(pageNumber)), HttpStatus.OK);
    }


    @PostMapping(Routes.REQUISITION_CREATION)
    @ResponseBody
    public ResponseEntity createAndApprovedBusinessTrip(@RequestBody String businessTrip) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BusinessTrip newBusinessTrip;
        try {
            newBusinessTrip = mapper.readValue(businessTrip, BusinessTrip.class);
            newBusinessTrip.clean();
        } catch (JsonMappingException | JsonParseException e) {
            return Result.response(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e){
            return Result.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            validate(newBusinessTrip);
        } catch (ValidationException ex) {
            return Result.response(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            tripService.createNewBusinessTrip(newBusinessTrip);
        } catch (UniquieException e) {
            return Result.response(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return Result.response(HttpStatus.OK);
    }

    @GetMapping(Routes.AMOUNT_OF_BUSINESS_TRIPS)
    @ResponseBody
    public ResponseEntity getAmount() {
        return Result.response(tripService.count(), HttpStatus.OK);
    }

    private void validate(@NotNull @Valid BusinessTrip businessTrip) {
        if (!businessTrip.getName().matches(Regex.FILTER.pattern())) {
            throw new ValidationException("Неправильно введено имя!");
        }

        if (!businessTrip.getTicketPrice().toString().matches(Regex.PRICE.pattern())) {
            throw new ValidationException("Некорректная цена");
        }

        if (!businessTrip.getPhone().matches(Regex.PHONE.pattern())) {
            throw new ValidationException("Некорректная номер телефона");
        }

        if (!businessTrip.getRateNumber().matches(Regex.RATE_NUMBER.pattern())) {
            throw new ValidationException("Неправильный рейс поездки");
        }

        if (!businessTrip.getPassportNumber().matches(Regex.PASSPORT.pattern())) {
            throw new ValidationException("Неправильный номер паспорта");
        }
    }

}
