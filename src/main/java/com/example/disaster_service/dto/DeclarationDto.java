package com.example.disaster_service.dto;

import lombok.Data;

@Data

public class DeclarationDto {

    private Long id;

    private String fullName;

    private Integer phoneNumber;

    private String address;

    private String description;

    private PointDto localisation;

}
