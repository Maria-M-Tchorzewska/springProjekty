package com.example.BookStoreFinalProject.entities;

import com.example.BookStoreFinalProject.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email jest wymagany")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Haslo jest wymagane")
    private String password;
    private String firstName;
    private String lastName;

    @NotBlank(message = "Podaj numer telefonu")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean active;

    private final LocalDate createdAt = LocalDate.now();

}

