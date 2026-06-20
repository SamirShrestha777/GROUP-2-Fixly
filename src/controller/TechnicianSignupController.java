package controller;

import dao.TechnicianDao;
import model.UserData;
import view.signupfortechnician;
import view.HomePage;
import javax.swing.JOptionPane;
import utils.NotificationEmail;
import utils.TechnicianApprovedEmail;

public class TechnicianSignupController {
    private final TechnicianDao techDao = new TechnicianDao();
    private final signupfortechnician view;
    private final HomePage homeView;

    public TechnicianSignupController(signupfortechnician view, HomePage homeView) {
        this.view = view;
        this.homeView = homeView;
        view.addSignupListener(e -> handleSignup());

        setPasswordPlaceholder(view.getPasswordField(), "Enter your password");
        setPasswordPlaceholder(view.getConfirmPasswordField(), "Confirm your password");

        view.getShowPasswordCheckbox().addActionListener(e -> {
            boolean show = view.getShowPasswordCheckbox().isSelected();
            String psw  = new String(view.getPasswordField().getPassword());
            String cpsw = new String(view.getConfirmPasswordField().getPassword());
            if (!psw.equals("Enter your password")) {
                view.getPasswordField().setEchoChar(show ? (char) 0 : '●');
            }
            if (!cpsw.equals("Confirm your password")) {
                view.getConfirmPasswordField().setEchoChar(show ? (char) 0 : '●');
            }
        });
    }

    public void open()  { view.setVisible(true); }
    public void close() { view.dispose(); }

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

    private void handleSignup() {
        String name     = view.getFullName();
        String email    = view.getEmail();
        String password = view.getPassword();
        String confirm  = view.getConfirmPassword();
        String spec     = view.getSpecialization();
        String certPath = view.getCertificationPath();

        if (name.isEmpty() || email.isEmpty() ||
            password.isEmpty() || password.equals("Enter your password")) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }

        if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(view,
                "Please enter a valid email address (e.g. example@gmail.com).");
            return;
        }

        if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            JOptionPane.showMessageDialog(view,
                "Password must be at least 8 characters,\n" +
                "include one uppercase letter and one number.");
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.");
            return;
        }

        if (spec.equals("Select Your Specialization")) {
            JOptionPane.showMessageDialog(view, "Please select a specialization.");
            return;
        }

        if (certPath.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please upload your certification.");
            return;
        }

        if (techDao.checkTechnician(email)) {
            JOptionPane.showMessageDialog(view, "Email already registered.");
            return;
        }

        UserData user = new UserData();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setSpecialization(spec);
        techDao.createTechnicianWithCert(user, certPath);

        JOptionPane.showMessageDialog(view,
            "Application submitted! You will receive an email once reviewed by admin.");
        close();
        homeView.setVisible(true);
    }
}