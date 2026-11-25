package com.prashant.kisaansathibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "farms")
@Getter
@Setter
@NoArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private Double landArea;

    @Column(nullable = false)
    private String soilType;

    @Column(nullable = false)
    private String irrigationType;

    private String previousCrop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

