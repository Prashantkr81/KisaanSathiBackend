package com.prashant.kisaansathibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FarmResponse {

    private Long id;
    private String farmName;
    private Double landArea;
    private String currentCrop;
    private String pastCrop;
    private LocalDate cropSowedDate;

    // Optional legacy metadata. New Farm screen does not require these fields.
    private String state;
    private String district;
    private String soilType;
    private String irrigationType;
}
