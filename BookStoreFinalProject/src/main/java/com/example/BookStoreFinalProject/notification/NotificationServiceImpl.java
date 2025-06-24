package com.example.BookStoreFinalProject.notification;

import com.example.BookStoreFinalProject.dtos.NotificationDTO;
import com.example.BookStoreFinalProject.entities.Notification;
import com.example.BookStoreFinalProject.enums.NotificationType;
import com.example.BookStoreFinalProject.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepo;

    @Override
    @Async
    public void sendEmail(NotificationDTO dto) {
        log.info("Triggering mail send to {}", dto.getRecipient());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getRecipient());
        message.setSubject(dto.getSubject());
        message.setText(dto.getBody());

        mailSender.send(message);

        Notification toStore = Notification.builder()
                .recipient(dto.getRecipient())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .orderReference(dto.getBookingReference())
                .type(NotificationType.EMAIL)
                .build();

        notificationRepo.save(toStore);
    }

}
