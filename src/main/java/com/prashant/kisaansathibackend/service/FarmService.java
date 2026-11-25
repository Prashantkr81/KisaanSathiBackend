package com.prashant.kisaansathibackend.service;


import com.prashant.kisaansathibackend.dto.FarmRequest;
import com.prashant.kisaansathibackend.dto.FarmResponse;
import com.prashant.kisaansathibackend.entity.Farm;
import com.prashant.kisaansathibackend.entity.User;
import com.prashant.kisaansathibackend.repository.FarmRepository;
import com.prashant.kisaansathibackend.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public FarmService(
            FarmRepository farmRepository,
            UserRepository userRepository) {
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FarmResponse addFarm(String phoneNumber, FarmRequest request) {

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Farm farm = new Farm();

        farm.setState(request.getState());
        farm.setDistrict(request.getDistrict());
        farm.setLandArea(request.getLandArea());
        farm.setSoilType(request.getSoilType());
        farm.setIrrigationType(request.getIrrigationType());
        farm.setPreviousCrop(request.getPreviousCrop());
        farm.setUser(user);

        return mapToResponse(farmRepository.save(farm));
    }

    @Transactional(readOnly = true)
    public List<FarmResponse> getMyFarms(String phoneNumber) {

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return farmRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private FarmResponse mapToResponse(Farm farm) {

        return new FarmResponse(
                farm.getId(),
                farm.getState(),
                farm.getDistrict(),
                farm.getLandArea(),
                farm.getSoilType(),
                farm.getIrrigationType(),
                farm.getPreviousCrop()
        );
    }
}

