package utils;

public class TechnicianRejectedEmail extends NotificationEmail {

    public TechnicianRejectedEmail(String toEmail, String recipientName) {
        super(toEmail, recipientName);
    }

    @Override
    public String buildSubject() {
        return "Fixly — Your Technician Application Status";
    }

    @Override
    public String buildBody() {
        return "Dear " + recipientName + ",\n\n"
            + "Thank you for applying to join Fixly as a technician. "
            + "After careful review, we regret to inform you that your "
            + "application has not been approved at this time.\n\n"
            + "You are welcome to reapply in the future with updated credentials.\n\n"
            + "Best regards,\nThe Fixly Team";
    }
}