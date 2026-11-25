package com.prashant.kisaansathibackend.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FarmRequest {

    @NotBlank(message = "Farm name is required")
    @Size(max = 100, message = "Farm name must be at most 100 characters")
    private String farmName;

    @NotNull(message = "Land area is required")
    @Positive(message = "Land area must be greater than zero")
    private Double landArea;

    // Optional: keep null when no crop is currently sown.
    @Size(max = 100)
    private String currentCrop;

    @Size(max = 100)
    private String pastCrop;

    // Optional: null when no current crop/sowing date is available.
    @PastOrPresent(message = "Crop sowed date cannot be in the future")
    private LocalDate cropSowedDate;
}
