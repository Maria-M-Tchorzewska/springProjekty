package com.example.BookStoreFinalProject.entities;

import com.example.BookStoreFinalProject.enums.OrderStatus;
import com.example.BookStoreFinalProject.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private BigDecimal totalPrice;
    private String orderReference;
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}