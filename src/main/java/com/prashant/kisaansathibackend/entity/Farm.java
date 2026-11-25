package com.prashant.kisaansathibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "farms")
@Getter
@Setter
@NoArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "farm_name", nullable = false, length = 100)
    private String farmName;

    // Area is stored in hectares, matching the crop prediction model input.
    @Column(name = "land_area", nullable = false)
    private Double landArea;

    @Column(name = "current_crop", length = 100)
    private String currentCrop;

    // Reuses the existing previous_crop database column to preserve old data.
    @Column(name = "previous_crop", length = 100)
    private String pastCrop;

    // Nullable for previously created farms that did not record a sowing date.
    @Column(name = "crop_sowed_date")
    private LocalDate cropSowedDate;

    // Legacy optional details are retained for existing records and future advisory use.
    private String state;
    private String district;
    private String soilType;
    private String irrigationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
