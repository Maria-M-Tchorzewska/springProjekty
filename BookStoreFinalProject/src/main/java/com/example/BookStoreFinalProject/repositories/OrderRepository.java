package com.example.BookStoreFinalProject.repositories;

import com.example.BookStoreFinalProject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    Optional<Order> findByOrderReference(String orderReference);
    List<Order> findByBookId(Long bookId);
}
