package com.prashant.kisaansathibackend.service;

import com.prashant.kisaansathibackend.dto.FarmerProfileResponse;
import com.prashant.kisaansathibackend.dto.UpdateFarmerRequest;
import com.prashant.kisaansathibackend.entity.User;
import com.prashant.kisaansathibackend.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class FarmerService {

    private final UserRepository userRepository;

    public FarmerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public FarmerProfileResponse getProfile(String phoneNumber) {

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new RuntimeException("Farmer not found"));

        return new FarmerProfileResponse(
                user.getId(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getRole()
        );
    }

    public FarmerProfileResponse updateProfile(
            String phoneNumber,
            UpdateFarmerRequest request) {

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new RuntimeException("Farmer not found"));

        user.setFullName(request.getFullName());

        User updatedUser = userRepository.save(user);

        return new FarmerProfileResponse(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getPhoneNumber(),
                updatedUser.getRole()
        );

    }
}
