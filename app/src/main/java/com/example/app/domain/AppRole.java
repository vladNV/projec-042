package com.example.app.domain;

/**
 * Application roles
 */
public enum AppRole {

    GUEST("GUEST"),
    BANNED("BANNED"),
    ADMIN("ADMIN"),
    USER("USER"),
    OTHER("OTHER");

    private String title;

    AppRole(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
