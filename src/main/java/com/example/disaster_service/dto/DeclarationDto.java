package com.example.disaster_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class DeclarationDto {

    private Long id;

    @NotBlank(message = "this is required")
    private String fullName;

    @NotNull(message = "Phone Number is required")
    private Integer phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private String description;


    @NotNull(message = "Localisation is required")
    private PointDto localisation;

}
