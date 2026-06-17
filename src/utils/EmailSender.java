package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    private static final String FROM_EMAIL = "fixlynepal@gmail.com";
    private static final String APP_PASSWORD = "sqxfmhiltfisicjm";

    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // use full path to avoid clash with utils.Session
        jakarta.mail.Session mailSession = jakarta.mail.Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
                }
            });

        Message message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
    }

    public static void sendOTP(String toEmail) throws MessagingException {
        String otp = OTPService.generateOTP();
        OTPService.saveOTP(toEmail, otp);
        String subject = "Your Password Reset OTP";
        String body = "Hello,\n\n"
                + "Your OTP for password reset is: " + otp + "\n\n"
                + "This code expires in 5 minutes and can only be used once.\n\n"
                + "If you did not request this, please ignore this email.";
        sendEmail(toEmail, subject, body);
    }
}