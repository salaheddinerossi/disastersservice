package com.example.disaster_service.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DisasterDto {

    private MultipartFile file;
    private String name;
    private String description;

}
