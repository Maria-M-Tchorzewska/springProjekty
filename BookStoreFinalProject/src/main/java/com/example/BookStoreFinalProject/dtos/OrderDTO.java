package com.example.BookStoreFinalProject.dtos;

import com.example.BookStoreFinalProject.enums.OrderStatus;
import com.example.BookStoreFinalProject.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private Long id;

    private UserDTO user;

    private BookDTO book;
    private Long bookId;

    private PaymentStatus paymentStatus;

    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private BigDecimal totalPrice;
    private String orderReference;
    private LocalDate createdAt;

    private OrderStatus orderStatus;
}
