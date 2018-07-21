package com.example.app.service.impl;

import com.example.app.domain.Transport;
import com.example.app.repository.TransportRepository;
import com.example.app.service.TransportService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public void putTransport(@NonNull Transport transport) {
        transportRepository.save(transport);
    }
}
