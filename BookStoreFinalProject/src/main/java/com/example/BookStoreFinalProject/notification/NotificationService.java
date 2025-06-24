package com.example.BookStoreFinalProject.notification;

import com.example.BookStoreFinalProject.dtos.NotificationDTO;

    public interface NotificationService {
        void sendEmail(NotificationDTO notificationDTO);
    }
