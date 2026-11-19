package com.prashant.kisaansathibackend.controller;

import com.prashant.kisaansathibackend.dto.AuthResponse;
import com.prashant.kisaansathibackend.dto.LoginRequest;
import com.prashant.kisaansathibackend.dto.RegisterRequest;
import com.prashant.kisaansathibackend.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(
            org.springframework.security.core.Authentication authentication) {

        return ResponseEntity.ok(
                "Authenticated user: " + authentication.getName()
        );
    }

}
