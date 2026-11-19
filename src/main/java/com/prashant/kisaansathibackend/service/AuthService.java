package com.prashant.kisaansathibackend.service;


import com.prashant.kisaansathibackend.dto.AuthResponse;
import com.prashant.kisaansathibackend.dto.LoginRequest;
import com.prashant.kisaansathibackend.dto.RegisterRequest;
import com.prashant.kisaansathibackend.entity.User;
import com.prashant.kisaansathibackend.repository.UserRepository;

import com.prashant.kisaansathibackend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException(
                    "Phone number is already registered");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());

        // Never store plain-text passwords
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        user.setRole("FARMER");

        User savedUser = userRepository.save(user);

        return new AuthResponse(
                "Registration successful",
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getPhoneNumber(),
                savedUser.getRole(),
                null
        );
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid phone number or password"));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {

            throw new IllegalArgumentException(
                    "Invalid phone number or password");
        }

        String token = jwtService.generateToken(user.getPhoneNumber());

        return new AuthResponse(
                "Login successful",
                user.getId(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getRole(),
                token
        );
    }

}