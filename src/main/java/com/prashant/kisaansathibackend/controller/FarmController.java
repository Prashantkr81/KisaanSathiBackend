package com.prashant.kisaansathibackend.controller;

import com.prashant.kisaansathibackend.dto.FarmRequest;
import com.prashant.kisaansathibackend.dto.FarmResponse;
import com.prashant.kisaansathibackend.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public ResponseEntity<FarmResponse> addFarm(
            Authentication authentication,
            @Valid @RequestBody FarmRequest request) {
        FarmResponse response = farmService.addFarm(
                authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FarmResponse>> getMyFarms(
            Authentication authentication) {
        return ResponseEntity.ok(
                farmService.getMyFarms(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponse> getFarmById(
            Authentication authentication,
            @PathVariable Long id) {
        return ResponseEntity.ok(
                farmService.getFarmById(authentication.getName(), id));
    }

}
