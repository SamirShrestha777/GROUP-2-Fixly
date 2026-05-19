package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

class OTPService {

    private static final Map<String, String> otpStore = new HashMap<>();
    private static final Map<String, Long> otpTimestamp = new HashMap<>();
    private static final long EXPIRY_MS = 5 * 60 * 1000;

    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static void saveOTP(String email, String otp) {
        otpStore.put(email, otp);
        otpTimestamp.put(email, System.currentTimeMillis());
    }

    public static boolean verifyOTP(String email, String enteredOTP) {
        String storedOTP = otpStore.get(email);
        Long timestamp = otpTimestamp.get(email);

        if (storedOTP == null || timestamp == null) return false;

        if (System.currentTimeMillis() - timestamp > EXPIRY_MS) {
            otpStore.remove(email);
            otpTimestamp.remove(email);
            return false;
        }

        if (storedOTP.equals(enteredOTP)) {
            otpStore.remove(email);
            otpTimestamp.remove(email);
            return true;
        }

        return false;
    }
}

public class EmailSender {

    private static final String FROM_EMAIL = "samirstha4253@gmail.com";
    private static final String APP_PASSWORD = "ponxrswxauipnxkn";

    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
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
