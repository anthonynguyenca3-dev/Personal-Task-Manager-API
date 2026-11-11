package com.anthonynguyen.project.dto;

public class RegistrationResponse {

    private final Long id;

    private final String email;

    public RegistrationResponse(Long id, String email) {

        this.id = id;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
