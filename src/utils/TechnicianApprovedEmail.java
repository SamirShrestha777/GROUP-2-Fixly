package utils;

public class TechnicianApprovedEmail extends NotificationEmail {

    public TechnicianApprovedEmail(String toEmail, String recipientName) {
        super(toEmail, recipientName);
    }

    @Override
    public String buildSubject() {
        return "Fixly — Your Technician Application has been Approved!";
    }

    @Override
    public String buildBody() {
        return "Dear " + recipientName + ",\n\n"
            + "Congratulations! Your application to join Fixly as a technician "
            + "has been reviewed and approved by our admin team.\n\n"
            + "You can now log in to the Fixly app using your registered "
            + "email and password.\n\n"
            + "Welcome to the Fixly team!\n\n"
            + "Best regards,\nThe Fixly Team";
    }
}