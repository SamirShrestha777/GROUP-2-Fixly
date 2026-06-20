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
import view.signupfortechnician;
import utils.Session;
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

        homeView.addTechSignupListener(e -> {
            close();
            signupfortechnician techView = new signupfortechnician();
            new TechnicianSignupController(techView, homeView);
            techView.setVisible(true);
        });

        setPlaceholder(homeView.getEmailField(), "Enter your email address");
        setPasswordPlaceholder(homeView.getPasswordField(), "Enter your password");

        javax.swing.ButtonGroup roleGroup = new javax.swing.ButtonGroup();
        roleGroup.add(homeView.getUserbtn());
        roleGroup.add(homeView.getAdminbtn());
        roleGroup.add(homeView.getTechbtn());
        homeView.getUserbtn().setSelected(true);
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

    private void setPasswordPlaceholder(javax.swing.JPasswordField field, String placeholder) {
        field.setEchoChar((char) 0);
        field.setText(placeholder);
        field.setForeground(java.awt.Color.GRAY);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                    field.setEchoChar('●');
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setEchoChar((char) 0);
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
            String password = new String(homeView.getPasswordField().getPassword()).trim();
            String role     = homeView.getSelectedRole();

            if (email.isEmpty() || email.equals("Enter your email address") ||
                password.isEmpty() || password.equals("Enter your password")) {
                JOptionPane.showMessageDialog(homeView, "Please fill in all fields.");
                return;
            }

            if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(homeView, "Please enter a valid email address.");
                return;
            }

            switch (role) {

                case "admin":
                    if (adminDao.loginAdmin(email, password)) {
                        adminDao.logAction("Admin logged in");
                        Session.setEmail(email);
                        Session.setRole("admin");
                        close();
                        AdminDashboard adminDash = new AdminDashboard();
                        new AdminController(adminDash);
                        adminDash.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(homeView,
                            "Invalid admin credentials.");
                    }
                    break;

                case "technician":
                    if (techDao.checkTechnician(email)) {
                        if (!techDao.isVerified(email)) {
                            JOptionPane.showMessageDialog(homeView,
                                "Your account is pending admin approval.\n" +
                                "You will receive an email once approved.");
                            return;
                        }
                    }
                    if (techDao.loginTechnician(email, password)) {
                        String techUsername   = techDao.getUsernameByEmail(email);
                        int techId            = techDao.getIdByEmail(email);
                        String specialization = techDao.getSpecializationByEmail(email);

                        Session.setUserId(techId);
                        Session.setEmail(email);
                        Session.setRole("technician");
                        Session.setSpecialization(specialization);

                        close();
                        TechnicianDashboard techDash = new TechnicianDashboard();
                        techDash.setTechnicianId(techId);
                        techDash.setUsername(techUsername);
                        new TechnicianController(techDash);
                        techDash.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(homeView,
                            "Invalid technician credentials.");
                    }
                    break;

                case "client":
                    UserData user = new UserData();
                    user.setEmail(email);
                    user.setPassword(password);
                    if (userDao.loginUser(user)) {
                        String username = userDao.getUsernameByEmail(email);
                        int userId      = userDao.getIdByEmail(email);

                        Session.setUserId(userId);
                        Session.setEmail(email);
                        Session.setRole("client");

                        close();
                        Dashboard dashboard = new Dashboard();
                        dashboard.setUsername(username);
                        new DashboardController(dashboard);
                        dashboard.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(homeView,
                            "Invalid user credentials.");
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(homeView,
                        "Please select a role to continue.");
            }
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
            ForgotPasswordPageController forgotController =
                new ForgotPasswordPageController(forgotView, homeView);
            forgotController.open();
        }
    }
}