package com.prashant.kisaansathibackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FarmResponse {

    private Long id;
    private String state;
    private String district;
    private Double landArea;
    private String soilType;
    private String irrigationType;
    private String previousCrop;
}

