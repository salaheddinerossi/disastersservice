package com.example.disaster_service.repository;

import com.example.disaster_service.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisasterRepository extends JpaRepository<Disaster,Long> {
}
