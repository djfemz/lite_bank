package dev.litebank.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import dev.litebank.dto.requests.EmailNotificationRequest;
import dev.litebank.dto.responses.EmailNotificationResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridMailService implements EmailNotificationService{
    @Override
    public EmailNotificationResponse notifyBy(EmailNotificationRequest notificationRequest) throws IOException {
        Email from = new Email("no-reply@litebank.ng");
        Email to = new Email(notificationRequest.getRecipient());
        Content content = new Content("text/plain", notificationRequest.getMailBody());
        Mail mail = new Mail(from, notificationRequest.getSubject(), to, content);
        SendGrid sg = new SendGrid("YOUR-API-KEY");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            EmailNotificationResponse emailNotificationResponse =
                    new EmailNotificationResponse();
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300)
                emailNotificationResponse.setStatus(true);
            else emailNotificationResponse.setStatus(false);
            return emailNotificationResponse;
        } catch (IOException ex) {
            throw ex;
        }
    }
}
