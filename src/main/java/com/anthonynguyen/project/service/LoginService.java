package com.anthonynguyen.project.service;

import com.anthonynguyen.project.domain.User;
import com.anthonynguyen.project.dto.LoginRequest;
import com.anthonynguyen.project.dto.LoginResponse;
import com.anthonynguyen.project.repository.UserRepository;
import com.anthonynguyen.project.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String email = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(email);

        User user = userOptional.orElseThrow();

        Long id = user.getId();

        String token = jwtService.generateToken(id);

        return new LoginResponse(id, email, token);

    }

}
