package com.example.app.service;

import com.example.app.model.Identity;
import org.springframework.stereotype.Service;

@Service
public interface IdentityService {

    Identity getAllInformation(Long id);

    Identity getAllInformation(String login);

}
