package com.example.disaster_service.controller;

import com.example.disaster_service.dto.DeclarationDto;
import com.example.disaster_service.model.Declaration;
import com.example.disaster_service.service.DeclarationService;
import com.example.disaster_service.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/declaration")
public class DeclarationController {

    @Value("${other.service.url}")
    private String authService;

    final
    UserService userService;
    final
    DeclarationService declarationService;

    public DeclarationController(DeclarationService declarationService, UserService userService) {
        this.declarationService = declarationService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createDeclaration(@RequestBody DeclarationDto declarationDto){


        Declaration declaration = declarationService.createDeclaration(declarationDto);
        return ResponseEntity.status(HttpStatus.OK).body(declaration);

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllDeclarations(@RequestHeader("Authorization") String token){
        Boolean isAdmin = userService.isAdmin(token,authService);

        if (isAdmin){
            return ResponseEntity.status(HttpStatus.OK).body(declarationService.getAllDeclarations());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" you don't have the permission to access this page");

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeclaration(@PathVariable Long id, @RequestHeader("Authorization") String token){

        Boolean isAdmin = userService.isAdmin(token,authService);

        if (isAdmin){
            return ResponseEntity.status(HttpStatus.OK).body(declarationService.getDeclaration(id));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you don't have the permission to access this page");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> archiveDeclaration(@PathVariable Long id, @RequestHeader("Authorization") String token){

        Boolean isAdmin = userService.isAdmin(token,authService);

        if (isAdmin){
            return ResponseEntity.status(HttpStatus.OK).body(declarationService.archiveDeclaration(id));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you don't have the permission to perform this action");

    }

}
