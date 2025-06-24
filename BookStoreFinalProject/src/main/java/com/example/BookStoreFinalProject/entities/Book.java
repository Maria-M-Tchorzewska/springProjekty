package com.example.BookStoreFinalProject.entities;

import com.example.BookStoreFinalProject.enums.BookGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BookGenre genre;

    @NotBlank(message = "Podaj tytul")
    private String title;

    @NotBlank(message = "Autor jest wymagany")
    private String author;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    private String description;

    private String coverImageUrl;
}
