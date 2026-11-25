package com.prashant.kisaansathibackend.service;

import com.prashant.kisaansathibackend.dto.FarmRequest;
import com.prashant.kisaansathibackend.dto.FarmResponse;
import com.prashant.kisaansathibackend.entity.Farm;
import com.prashant.kisaansathibackend.entity.User;
import com.prashant.kisaansathibackend.repository.FarmRepository;
import com.prashant.kisaansathibackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public FarmService(FarmRepository farmRepository, UserRepository userRepository) {
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FarmResponse addFarm(String phoneNumber, FarmRequest request) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        Farm farm = new Farm();
        farm.setFarmName(request.getFarmName().trim());
        farm.setLandArea(request.getLandArea());
        farm.setCurrentCrop(normalizeOptional(request.getCurrentCrop()));
        farm.setPastCrop(normalizeOptional(request.getPastCrop()));
        farm.setCropSowedDate(request.getCropSowedDate());
        farm.setUser(user);

        return toResponse(farmRepository.save(farm));
    }

    @Transactional(readOnly = true)
    public List<FarmResponse> getMyFarms(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        return farmRepository.findByUserIdOrderByIdDesc(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FarmResponse getFarmById(String phoneNumber, Long farmId) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        Farm farm = farmRepository.findByIdAndUserId(farmId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Farm not found"));

        return toResponse(farm);
    }

    private FarmResponse toResponse(Farm farm) {
        return new FarmResponse(
                farm.getId(),
                farm.getFarmName(),
                farm.getLandArea(),
                farm.getCurrentCrop(),
                farm.getPastCrop(),
                farm.getCropSowedDate(),
                farm.getState(),
                farm.getDistrict(),
                farm.getSoilType(),
                farm.getIrrigationType()
        );
    }

    private String normalizeOptional(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
