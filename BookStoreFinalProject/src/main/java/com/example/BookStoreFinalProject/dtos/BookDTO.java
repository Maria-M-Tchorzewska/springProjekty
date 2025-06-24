package com.example.BookStoreFinalProject.dtos;

import com.example.BookStoreFinalProject.enums.BookGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private Long id;

    private BookGenre genre;

    private String title;

    private String author;

    private BigDecimal price;

    private String description;

    private String coverImageUrl;
}
