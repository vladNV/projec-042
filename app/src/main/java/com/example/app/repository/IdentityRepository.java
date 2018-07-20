package com.example.app.repository;

import com.example.app.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, Long> {

    Identity findByLogin(String login);

}
