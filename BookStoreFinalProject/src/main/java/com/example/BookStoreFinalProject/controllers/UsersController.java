package com.example.BookStoreFinalProject.controllers;

import com.example.BookStoreFinalProject.dtos.Response;
import com.example.BookStoreFinalProject.dtos.UserDTO;
import com.example.BookStoreFinalProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateOwnAccount(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUserDetails(userDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteOwnAccount() {
        return ResponseEntity.ok(userService.deleteOwnAccount());
    }

    @GetMapping("/profile")
    public ResponseEntity<Response> getOwnProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    @GetMapping("/orders")
    public ResponseEntity<Response> getUserOrderHistory() {
        return ResponseEntity.ok(userService.getUserOrderHistory());
    }

}
