package com.example.disaster_service.controller;


import com.example.disaster_service.dto.DisasterDto;
import com.example.disaster_service.model.Disaster;
import com.example.disaster_service.service.DisasterService;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/disaster")
public class DisasterController {

    @Autowired
    DisasterService disasterService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadDisaster(@ModelAttribute DisasterDto disasterDTO) throws IOException, ParseException {
            Disaster disaster = disasterService.createDisaster(disasterDTO);

            return ResponseEntity.ok(disaster);
    }

}
