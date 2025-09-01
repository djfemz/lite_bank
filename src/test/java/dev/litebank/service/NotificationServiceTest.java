package dev.litebank.service;

import dev.litebank.dto.requests.EmailNotificationRequest;
import dev.litebank.dto.requests.Recipient;
import dev.litebank.dto.responses.EmailNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void testSendEmailNotification() throws IOException {
        EmailNotificationRequest notificationRequest = new EmailNotificationRequest();
        notificationRequest.setTo(List.of(new Recipient("John", "behew90829@ahanim.com")));
        notificationRequest.setSubject("Test Notification");
        notificationRequest.setHtmlContent("Hello World");
        EmailNotificationResponse response = notificationService.notifyBy(notificationRequest);
        assertThat(response).isNotNull();
        assertThat(response.isStatus()).isTrue();
    }
}
