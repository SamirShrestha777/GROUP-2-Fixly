package utils;

import jakarta.mail.MessagingException;

public abstract class NotificationEmail {
    protected String toEmail;
    protected String recipientName;

    public NotificationEmail(String toEmail, String recipientName) {
        this.toEmail = toEmail;
        this.recipientName = recipientName;
    }

    public abstract String buildSubject();
    public abstract String buildBody();

    public void send() throws MessagingException {
        EmailSender.sendEmail(toEmail, buildSubject(), buildBody());
    }
}