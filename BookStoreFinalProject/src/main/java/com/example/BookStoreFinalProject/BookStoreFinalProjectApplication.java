package com.example.BookStoreFinalProject;

import com.example.BookStoreFinalProject.dtos.NotificationDTO;
import com.example.BookStoreFinalProject.notification.NotificationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class BookStoreFinalProjectApplication {

	private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreFinalProjectApplication.class, args);
	}

	@PostConstruct
	public void sendEmail() {
		NotificationDTO notif = NotificationDTO.builder()
				.recipient("tchmaria1@gmail.com")
				.subject("testest")
				.body("testest")
				.build();
		notificationService.sendEmail(notif);
	}

}
