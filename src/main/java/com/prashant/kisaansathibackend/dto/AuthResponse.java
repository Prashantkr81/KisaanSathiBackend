package com.prashant.kisaansathibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String message;
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private String role;
    private String token;
}
