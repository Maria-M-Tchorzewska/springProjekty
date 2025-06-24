package com.example.BookStoreFinalProject.services;

import com.example.BookStoreFinalProject.entities.Order;
import com.example.BookStoreFinalProject.repositories.OrderRepository;
import com.example.BookStoreFinalProject.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByBookId(Long bookId) {
        return orderRepository.findByBookId(bookId);
    }

    @Override
    public Optional<Order> getOrderByReference(String reference) {
        return orderRepository.findByOrderReference(reference);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
