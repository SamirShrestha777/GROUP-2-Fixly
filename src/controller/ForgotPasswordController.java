package controller;

import dao.UserDao;
import model.UserData;
import utils.EmailSender;
import utils.OTPService;
import javax.swing.JOptionPane;

public class ForgotPasswordController {

    private final UserDao userDao = new UserDao();

    public boolean sendOtp(String email) {
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your email.");
            return false;
        }
        UserData user = new UserData();
        user.setEmail(email);
        boolean exists = userDao.checkUser(user);
        if (!exists) {
            JOptionPane.showMessageDialog(null, "Email not found.");
            return false;
        }
        try {
            EmailSender.sendOTP(email);
            JOptionPane.showMessageDialog(null, "OTP sent to " + email);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to send OTP: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyOtp(String email, String enteredOtp) {
        boolean valid = OTPService.verifyOTP(email, enteredOtp);
        if (!valid) {
            JOptionPane.showMessageDialog(null, "Invalid or expired OTP.");
        }
        return valid;
    }

    public boolean resetPassword(String email, String newPassword, String confirmPassword) {
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty.");
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return false;
        }
        boolean success = userDao.resetPassword(email, newPassword);
        if (success) {
            JOptionPane.showMessageDialog(null, "Password reset successfully!");
        }
        return success;
    }
}