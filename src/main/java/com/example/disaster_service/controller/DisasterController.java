package com.example.disaster_service.controller;


import com.example.disaster_service.dto.DisasterDto;
import com.example.disaster_service.dto.UserDetailsDto;
import com.example.disaster_service.model.Disaster;
import com.example.disaster_service.service.DisasterService;
import com.example.disaster_service.service.UserService;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/disaster")
public class DisasterController {

    final
    DisasterService disasterService;

    private final UserService userService;

    public DisasterController(DisasterService disasterService, UserService userService) {
        this.disasterService = disasterService;
        this.userService = userService;
    }

    @Value("${other.service.url}")
    private String otherServiceUrl; // URL injected from properties



    @PostMapping("/upload")
    public ResponseEntity<?> uploadDisaster(@ModelAttribute DisasterDto disasterDTO,@RequestHeader("Authorization") String token) throws IOException, ParseException {

        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        UserDetailsDto userDetails = userService.getUserDetailsFromOtherService(otherServiceUrl, actualToken);
        boolean isAdmin = userDetails.getRole().equals("ROLE_ADMIN");
        System.out.println(token);

        System.out.println(isAdmin);

        if (isAdmin) {
            Disaster disaster = disasterService.createDisaster(disasterDTO);
            return ResponseEntity.ok("disaster added");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to perform this action.");
        }
    }

}
