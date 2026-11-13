package com.anthonynguyen.project.controller;

import com.anthonynguyen.project.dto.LoginRequest;
import com.anthonynguyen.project.dto.LoginResponse;
import com.anthonynguyen.project.dto.RegistrationRequest;
import com.anthonynguyen.project.dto.RegistrationResponse;
import com.anthonynguyen.project.service.LoginService;
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
    private final LoginService loginService;

    public AuthController(RegistrationService registrationService, LoginService loginService){
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<RegistrationResponse> createUser(@Valid @RequestBody RegistrationRequest addUser) {

        RegistrationResponse user = registrationService.registerUser(addUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest logUser) {

        LoginResponse user = loginService.loginUser(logUser);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}
