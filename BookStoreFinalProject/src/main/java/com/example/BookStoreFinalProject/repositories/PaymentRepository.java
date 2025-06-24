package com.example.BookStoreFinalProject.repositories;

import com.example.BookStoreFinalProject.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
