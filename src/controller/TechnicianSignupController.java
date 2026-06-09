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
    }

    public void open() { view.setVisible(true); }
    public void close() { view.dispose(); }

    private void handleSignup() {
        String name     = view.getFullName();
        String email    = view.getEmail();
        String password = view.getPassword();
        String confirm  = view.getConfirmPassword();
        String spec     = view.getSpecialization();
        String certPath = view.getCertificationPath();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.");
            return;
        }
        if (spec.equals("Select Specialization")) {
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