package com.prashant.kisaansathibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FarmerProfileResponse {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String role;
}
