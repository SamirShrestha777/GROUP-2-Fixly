package controller;

import dao.TechnicianDao;
import model.UserData;
import view.AdminDashboard;
import view.HomePage;
import utils.Session;
import utils.TechnicianApprovedEmail;
import utils.TechnicianRejectedEmail;
import utils.NotificationEmail;
import javax.swing.JOptionPane;
import java.util.List;

public class AdminController {

    private final AdminDashboard view;
    private final TechnicianDao techDao = new TechnicianDao();

    public AdminController(AdminDashboard view) {
        this.view = view;
        view.initPendingPanel();
        loadPending();
        wireButtons();
    }

    private void loadPending() {
        List<UserData> pending = techDao.getPendingTechnicians();
        view.loadPendingTechnicians(pending, this::approveTechnician, this::rejectTechnician, this::viewCertificate);
    }

    private void approveTechnician(int techId) {
        UserData tech = techDao.getTechnicianById(techId);
        if (tech == null) return;

        boolean updated = techDao.approveTechnician(techId);
        if (updated) {
            try {
                NotificationEmail email = new TechnicianApprovedEmail(
                    tech.getEmail(), tech.getUsername());
                email.send();
                JOptionPane.showMessageDialog(view,
                    tech.getUsername() + " approved and notified by email.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view,
                    "Approved but email failed: " + e.getMessage());
            }
            loadPending();
        }
    }

    private void rejectTechnician(int techId) {
        UserData tech = techDao.getTechnicianById(techId);
        if (tech == null) return;

        int confirm = JOptionPane.showConfirmDialog(view,
            "Reject " + tech.getUsername() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean updated = techDao.rejectTechnician(techId);
        if (updated) {
            try {
                NotificationEmail email = new TechnicianRejectedEmail(
                    tech.getEmail(), tech.getUsername());
                email.send();
                JOptionPane.showMessageDialog(view,
                    tech.getUsername() + " rejected and notified by email.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view,
                    "Rejected but email failed: " + e.getMessage());
            }
            loadPending();
        }
    }

    private void viewCertificate(int techId) {
        UserData tech = techDao.getTechnicianById(techId);
        if (tech == null) return;

        String certPath = tech.getCertificationPath();
        if (certPath == null || certPath.isBlank()) {
            JOptionPane.showMessageDialog(view,
                tech.getUsername() + " has no certification file on record.");
            return;
        }
        view.showCertificatePreview(certPath);
    }

    private void wireButtons() {
        view.addLogoutListener(e -> {
            Session.clear();
            view.dispose();
            HomePage homeView = new HomePage();
            new HomeController(homeView);
            homeView.setVisible(true);
        });
    }
}