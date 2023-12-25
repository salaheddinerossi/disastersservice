package com.example.disaster_service.repository;

import com.example.disaster_service.model.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationRepository extends JpaRepository<Declaration,Long> {
}
