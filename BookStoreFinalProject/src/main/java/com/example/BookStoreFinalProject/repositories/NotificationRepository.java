package com.example.BookStoreFinalProject.repositories;

import com.example.BookStoreFinalProject.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
