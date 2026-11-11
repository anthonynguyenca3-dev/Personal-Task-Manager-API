package com.anthonynguyen.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Email must be given")
    @Email(message = "Email must be in valid format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password must be given")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    protected User() {}

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

    public long getId() {
        return id;
    }

}
