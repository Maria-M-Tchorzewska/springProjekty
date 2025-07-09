package com.example.zadanie6.controller;

import com.example.zadanie6.model.Rental;
import com.example.zadanie6.service.RentalService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }


    @PostMapping("/rent")
    public Rental rent(@RequestParam String vehicleId, Authentication authentication) {
        String username = authentication.getName();
        return service.rent(vehicleId, username);
    }

    @PostMapping("/return")
    public boolean returnRental(@RequestParam String vehicleId, Authentication authentication) {
        String username = authentication.getName();
        return service.returnRental(vehicleId, username);
    }


    @GetMapping
    public List<Rental> all() {
        return service.findAll();
    }
}
