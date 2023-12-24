package com.example.disaster_service.repository;

import com.example.disaster_service.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
}
