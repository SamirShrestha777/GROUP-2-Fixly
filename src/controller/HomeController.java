package controller;

import dao.UserDao;
import model.UserData;
import view.HomePage;
import view.SignupPage;
import view.ForgotPassword;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {

    private final UserDao userDao = new UserDao();
    private final HomePage homeView;

    public HomeController(HomePage homeView) {
        this.homeView = homeView;
        homeView.addLoginListener(new LoginListener());
        homeView.addSignupListener(new SignupListener());
        homeView.addForgotPasswordListener(new ForgotPasswordListener());

        setPlaceholder(homeView.getEmailField(), "Enter your email address");
        setPlaceholder(homeView.getPasswordField(), "Enter your password");
    }

    public void open()  { homeView.setVisible(true); }
    public void close() { homeView.dispose(); }

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

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email    = homeView.getEmailField().getText().trim();
            String password = homeView.getPasswordField().getText().trim();

            if (email.isEmpty() || email.equals("Enter your email address") ||
                password.isEmpty() || password.equals("Enter your password")) {
                JOptionPane.showMessageDialog(homeView, "Please fill in all fields.");
                return;
            }

            UserData user = new UserData();
            user.setEmail(email);
            user.setPassword(password);

            boolean valid = userDao.loginUser(user);
            if (valid) {
                JOptionPane.showMessageDialog(homeView, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(homeView, "Invalid email or password.");
            }
        }
    }

    class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            SignupPage signupView = new SignupPage();
            SignupController signupController = new SignupController(signupView);
            signupController.open();
        }
    }

    class ForgotPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            ForgotPassword forgotView = new ForgotPassword();
            ForgotPasswordPageController forgotController = new ForgotPasswordPageController(forgotView);
            forgotController.open();
        }
    }
}