package com.example.BookStoreFinalProject.dtos;

import com.example.BookStoreFinalProject.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int status;
    private String message;

    private String token;
    private UserRole role;
    private Boolean active;
    private String expirationTime;

    private UserDTO user;
    private List<UserDTO> users;

    private OrderDTO order;
    private List<OrderDTO> orders;

    private BookDTO book;
    private List<BookDTO> books;

    private String transactionId;
    private PaymentDTO payment;
    private List<PaymentDTO> payments;

    private NotificationDTO notification;
    private List<NotificationDTO> notifications;

    private final LocalDateTime timestamp = LocalDateTime.now();
}
