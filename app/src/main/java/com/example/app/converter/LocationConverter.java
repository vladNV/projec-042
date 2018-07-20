package com.example.app.converter;

import com.example.app.domain.Facility;
import com.example.app.model.Location;
import org.springframework.lang.NonNull;

public class LocationConverter {

    public static Location toModel(@NonNull Facility facility) {
        Location location = new Location();

        location.setId(facility.getId());
        location.setDirection(facility.getDirection());
        location.setTitle(facility.getTitle());

        return location;
    }

}
