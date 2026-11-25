package com.prashant.kisaansathibackend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmRequest {

    @NotBlank
    private String state;

    @NotBlank
    private String district;

    @NotNull
    @Positive
    private Double landArea;

    @NotBlank
    private String soilType;

    @NotBlank
    private String irrigationType;

    private String previousCrop;
}

