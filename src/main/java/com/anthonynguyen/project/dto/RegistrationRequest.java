package com.anthonynguyen.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {

    @NotBlank(message = "Email must be given")
    @Email(message = "Email must be in valid format")
    private String email;

    @NotBlank(message = "Password must be given")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

