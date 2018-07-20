package com.example.app.service;

import com.example.app.domain.Transport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransportService {

    List<Transport> getAllTransports();

    void putTransport(Transport transport);

}
