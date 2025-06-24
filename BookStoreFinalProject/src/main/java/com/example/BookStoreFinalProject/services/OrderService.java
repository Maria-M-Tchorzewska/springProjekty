package com.example.BookStoreFinalProject.services;

import com.example.BookStoreFinalProject.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getOrdersByBookId(Long bookId);
    Optional<Order> getOrderByReference(String reference);
    void deleteOrder(Long id);
}
