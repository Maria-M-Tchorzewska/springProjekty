package com.example.zadanie6.controller;

import com.example.zadanie6.repository.RoleRepository;
import com.example.zadanie6.repository.UserRepository;
import com.example.zadanie6.users.Role;
import com.example.zadanie6.users.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setDeleted(true);
        userRepository.save(user);
        return ResponseEntity.ok("User soft-deleted.");
    }

    @PutMapping("/users/{id}/roles")
    public ResponseEntity<String> updateRoles(@PathVariable Long id, @RequestBody Set<String> roles) {
        User user = userRepository.findById(id).orElseThrow();
        Set<Role> newRoles = roles.stream()
                .map(role -> roleRepository.findByName(role).orElseThrow())
                .collect(Collectors.toSet());
        user.setRoles(newRoles);
        userRepository.save(user);
        return ResponseEntity.ok("User roles updated.");
    }
}
