package com.example.BookStoreFinalProject.controllers;

import com.example.BookStoreFinalProject.dtos.LoginRequest;
import com.example.BookStoreFinalProject.dtos.RegistrationRequest;
import com.example.BookStoreFinalProject.dtos.Response;
import com.example.BookStoreFinalProject.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }
}
