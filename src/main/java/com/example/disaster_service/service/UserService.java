package com.example.disaster_service.service;

import com.example.disaster_service.dto.UserDetailsDto;

public interface UserService {
    public UserDetailsDto getUserDetailsFromOtherService(String serviceUrl, String token);
}
