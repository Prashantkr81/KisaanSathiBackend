package com.prashant.kisaansathibackend.controller;


import com.prashant.kisaansathibackend.dto.FarmerProfileResponse;
import com.prashant.kisaansathibackend.dto.UpdateFarmerRequest;
import com.prashant.kisaansathibackend.service.FarmerService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping("/profile")
    public ResponseEntity<FarmerProfileResponse> getProfile(
            Authentication authentication) {

        return ResponseEntity.ok(
                farmerService.getProfile(authentication.getName())
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<FarmerProfileResponse> updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateFarmerRequest request) {

        return ResponseEntity.ok(
                farmerService.updateProfile(
                        authentication.getName(), request)
        );
    }

}
