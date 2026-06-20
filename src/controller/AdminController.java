package controller;

import dao.TechnicianDao;
import model.UserData;
import view.AdminDashboard;
import view.HomePage;
import java.util.List;

public class AdminController {

    private final AdminDashboard view;
    private final TechnicianDao techDao = new TechnicianDao();

    public AdminController(AdminDashboard view) {
        this.view = view;
        view.initPendingPanel();
        view.addLogoutListener(e -> handleLogout());
        loadPending();
    }

    private void loadPending() {
        List<UserData> pending = techDao.getPendingTechnicians();
        view.loadPendingTechnicians(
            pending,
            this::approveTechnician,
            this::rejectTechnician,
            this::viewCertificate
        );
    }

    private void approveTechnician(int techId) {
        techDao.approveTechnician(techId);

        UserData tech = techDao.getTechnicianById(techId);
        if (tech != null && tech.getEmail() != null) {
            try {
                new utils.TechnicianApprovedEmail(tech.getEmail(), tech.getUsername()).send();
            } catch (jakarta.mail.MessagingException ex) {
                System.out.println("Failed to send approval email: " + ex.getMessage());
            }
        }

        loadPending();
    }

    private void rejectTechnician(int techId) {
        techDao.rejectTechnician(techId);

        UserData tech = techDao.getTechnicianById(techId);
        if (tech != null && tech.getEmail() != null) {
            try {
                new utils.TechnicianRejectedEmail(tech.getEmail(), tech.getUsername()).send();
            } catch (jakarta.mail.MessagingException ex) {
                System.out.println("Failed to send rejection email: " + ex.getMessage());
            }
        }

        loadPending();
    }

    private void viewCertificate(int techId) {
        String certPath = techDao.getCertificationPathById(techId);
        new view.CertificatePreviewDialog(view, certPath).setVisible(true);
    }

    private void handleLogout() {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            view, "Are you sure you want to logout?",
            "Logout", javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            view.dispose();
            HomePage homeView = new HomePage();
            new HomeController(homeView);
            homeView.setVisible(true);
        }
    }
}