package com.anthonynguyen.project.controller;

import com.anthonynguyen.project.dto.RegistrationRequest;
import com.anthonynguyen.project.dto.RegistrationResponse;
import com.anthonynguyen.project.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<RegistrationResponse> createUser(@Valid @RequestBody RegistrationRequest addUser) {

        RegistrationResponse user = registrationService.registerUser(addUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
