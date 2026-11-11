package com.anthonynguyen.project.service;

import com.anthonynguyen.project.domain.User;
import com.anthonynguyen.project.dto.RegistrationRequest;
import com.anthonynguyen.project.dto.RegistrationResponse;
import com.anthonynguyen.project.exception.EmailAlreadyExistsException;
import com.anthonynguyen.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {

        if(userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already in use. Please use another email.");
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        User user = new User(registrationRequest.getEmail(), encodedPassword);

        User savedUser = userRepository.save(user);

        return new RegistrationResponse(savedUser.getId(), savedUser.getEmail());

    }



}
