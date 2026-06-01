package controller;

import dao.UserDao;
import dao.TechnicianDao;
import dao.AdminDao;
import model.UserData;
import view.Dashboard;
import view.HomePage;
import view.SignupPage;
import view.ForgotPassword;
import view.TechnicianDashboard;
import view.AdminDashboard;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {
    private final UserDao userDao = new UserDao();
    private final TechnicianDao techDao = new TechnicianDao();
    private final AdminDao adminDao = new AdminDao();
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
    public void close() { homeView.setVisible(false); }

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

            // 1 — Check admin first
            if (adminDao.loginAdmin(email, password)) {
                adminDao.logAction("Admin logged in");
                close();
                AdminDashboard adminDash = new AdminDashboard();
                adminDash.setVisible(true);
                return;
            }

            // 2 — Check technicians
            if (techDao.loginTechnician(email, password)) {
                String username = techDao.getUsernameByEmail(email);
                int techId = techDao.getIdByEmail(email);
                close();
                TechnicianDashboard techDash = new TechnicianDashboard();
                techDash.setTechnicianId(techId);
                techDash.setUsername(username);
                techDash.setVisible(true);
                return;
            }

            // 3 — Check users
            UserData user = new UserData();
            user.setEmail(email);
            user.setPassword(password);
            if (userDao.loginUser(user)) {
                String username = userDao.getUsernameByEmail(email);
                close();
                Dashboard dashboard = new Dashboard();
                dashboard.setUsername(username);
                dashboard.setVisible(true);
                return;
            }

            // Nothing matched
            JOptionPane.showMessageDialog(homeView, "Invalid email or password.");
        }
    }

    class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            SignupPage signupView = new SignupPage();
            SignupController signupController = new SignupController(signupView, homeView);
            signupController.open();
        }
    }

    class ForgotPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            ForgotPassword forgotView = new ForgotPassword();
            ForgotPasswordPageController forgotController = new ForgotPasswordPageController(forgotView, homeView);
            forgotController.open();
        }
    }
}