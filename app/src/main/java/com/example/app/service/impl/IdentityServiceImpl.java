package com.example.app.service.impl;

import com.example.app.converter.IdentityConverter;
import com.example.app.model.Identity;
import com.example.app.repository.IdentityRepository;
import com.example.app.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private IdentityRepository identityRepository;

    @Override
    public Identity getAllInformation(Long id) {
        return IdentityConverter.toModel(identityRepository.findById(id).orElse(null));
    }

    @Override
    public Identity getAllInformation(String login) {
        return IdentityConverter.toModel(identityRepository.findByLogin(login));
    }
}
