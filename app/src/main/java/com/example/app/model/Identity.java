package com.example.app.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Identity {
    private String login;
    private Long id;
    private LocalDateTime registrationDate;
    private String role;

    public void setRole(String role) {
        this.role = role.replace("ROLE_", "");
    }
}
