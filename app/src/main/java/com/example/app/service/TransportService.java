package com.example.app.service;

import com.example.app.domain.Transport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransportService {

    void putTransport(Transport transport);

}
