package controller;

import view.ForgotPassword;
import view.HomePage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordPageController {

    private final ForgotPassword forgotView;
    private final HomePage homeView;
    private final ForgotPasswordController forgotController = new ForgotPasswordController();

    public ForgotPasswordPageController(ForgotPassword forgotView, HomePage homeView) {
        this.forgotView = forgotView;
        this.homeView = homeView;
        forgotView.addSendOtpListener(new SendOtpListener());
        forgotView.addConfirmOtpListener(new ConfirmOtpListener());
        forgotView.addBackToLoginListener(new BackToLoginListener());

        setPlaceholder(forgotView.getEmailField(), "Enter your email address");
        setPlaceholder(forgotView.getOtpField(), "Enter the OTP");
    }

    public void open()  { forgotView.setVisible(true); }
    public void close() { forgotView.dispose(); }

    private void setPlaceholder(javax.swing.JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(java.awt.Color.GRAY);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    class SendOtpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = forgotView.getEmailField().getText().trim();
            boolean isUser = forgotView.isUserSelected();
            forgotController.sendOtp(email, isUser);
        }
    }

    class ConfirmOtpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = forgotView.getEmailField().getText().trim();
            String otp   = forgotView.getOtpField().getText().trim();

            boolean verified = forgotController.verifyOtp(email, otp);
            if (verified) {
                String newPassword     = javax.swing.JOptionPane.showInputDialog(forgotView, "Enter new password:");
                // Bug 3 fix: guard null if user cancelled the dialog
                if (newPassword == null) return;
                String confirmPassword = javax.swing.JOptionPane.showInputDialog(forgotView, "Confirm new password:");
                if (confirmPassword == null) return;

                // Bug 3 fix: enforce same strength rules as signup
                if (!newPassword.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                    javax.swing.JOptionPane.showMessageDialog(forgotView,
                        "Password must be at least 8 characters,\ninclude one uppercase letter and one number.");
                    return;
                }

                boolean isUser = forgotView.isUserSelected();
                boolean reset = forgotController.resetPassword(email, newPassword, confirmPassword, isUser);
                if (reset) {
                    close();
                    homeView.setVisible(true);
                }
            }
        }
    }

    class BackToLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            homeView.setVisible(true);
        }
    }
}