package controller;

import dao.TechnicianDao;
import model.UserData;
import view.AdminDashboard;
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
        loadPending();
    }

    private void rejectTechnician(int techId) {
        techDao.rejectTechnician(techId);
        loadPending();
    }

    private void viewCertificate(int techId) {
        String certPath = techDao.getCertificationPathById(techId);
        view.showCertificatePreview(certPath);
    }

    private void handleLogout() {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            view, "Are you sure you want to logout?",
            "Logout", javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            view.dispose();
            view.HomePage homeView = new view.HomePage();
            new HomeController(homeView);
            homeView.setVisible(true);
        }
    }
}