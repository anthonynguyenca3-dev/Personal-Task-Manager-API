package com.anthonynguyen.project.dto;

public class LoginResponse {

    private final Long id;

    private final String email;

    private final String token;

    public LoginResponse(Long id, String email, String token) {

        this.id = id;
        this.email = email;
        this.token = token;

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}