package com.example.app.controller;

import com.example.app.converter.LocationConverter;
import com.example.app.model.Location;
import com.example.app.service.FacilityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/search/name/{like}")
    @ResponseBody
    public ResponseEntity<List<Location>> findByNameOfPlace(@PathVariable(name = "like") String like) {
        if (StringUtils.isBlank(like)) {
            return Result.response(HttpStatus.BAD_REQUEST);
        }
        return Result.response(facilityService.findFacilitiesByTitle(like).stream()
                .map(LocationConverter::toModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/search/direction/{like}")
    public ResponseEntity<List<Location>> findByDirection(@PathVariable(name = "like") String like) {
        if (StringUtils.isBlank(like)) {
            return Result.response(HttpStatus.BAD_REQUEST);
        }
        return Result.response(facilityService.findFacilitiesByDirection(like).stream()
                .map(LocationConverter::toModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
