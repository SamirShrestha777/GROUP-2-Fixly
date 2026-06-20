package controller;

import dao.TechnicianDao;
import dao.AppointmentDao;
import dao.ReviewDao;
import model.UserData;
import model.Appointment;
import model.Review;
import view.AdminDashboard;
import view.HomePage;
import java.util.List;

public class AdminController {
    private final AdminDashboard view;
    private final TechnicianDao techDao = new TechnicianDao();
    private final AppointmentDao appointmentDao = new AppointmentDao();
    private final ReviewDao reviewDao = new ReviewDao();

    public AdminController(AdminDashboard view) {
        this.view = view;
        view.initPendingPanel();
        view.initMonitorPanel();
        view.initTechnicianPanel();
        view.initAdminHistoryPanel();
        view.initReviewsPanel();
        view.addLogoutListener(e -> handleLogout());
        view.addVerifyNavListener(e -> {
            view.setCurrentMode("");
            view.showVerifyPanel();
            view.setFilterButtonsVisible(false);
            loadPending();
        });
        view.addMonitorNavListener(e -> {
            view.configureForMonitorMode();
            view.showMonitorPanel();
            loadPendingPayments();
        });
        view.addTechnicianNavListener(e -> {
            view.setCurrentMode("");
            view.showTechnicianPanel();
            view.setFilterButtonsVisible(false);
            loadTechnicians();
        });
        view.addHistoryNavListener(e -> {
            view.configureForHistoryMode();
            view.showAdminHistoryPanel();
            loadAdminHistory("all");
        });
        
        view.addFilterAllListener(e -> {
            if ("monitor".equals(view.getCurrentMode())) {
                view.showMonitorPanel();
                loadPendingPayments();
            } else if ("history".equals(view.getCurrentMode())) {
                loadAdminHistory("all");
            }
        });
        view.addFilterPendingListener(e -> {
            if ("monitor".equals(view.getCurrentMode())) {
                view.showReviewsPanel();
                loadReviews();
            } else if ("history".equals(view.getCurrentMode())) {
                loadAdminHistory("pending");
            }
        });
        view.addFilterApprovedListener(e -> {
            if ("history".equals(view.getCurrentMode())) loadAdminHistory("approved");
        });
        view.addFilterRejectedListener(e -> {
            if ("history".equals(view.getCurrentMode())) loadAdminHistory("rejected");
        });

        view.setFilterButtonsVisible(false);
        loadPending();
    }

    private void loadAdminHistory(String status) {
        List<UserData> history = techDao.getTechniciansByApplicationStatus(status);
        view.loadAdminHistory(history);
    }

    private void loadPending() {
        List<UserData> pending = techDao.getPendingTechnicians();
        view.loadPendingTechnicians(
                pending,
                this::approveTechnician,
                this::rejectTechnician,
                this::viewCertificate);
    }

    private void loadTechnicians() {
        List<UserData> technicians = techDao.getApprovedTechnicians();
        view.loadTechnicians(technicians);
    }

    private void approveTechnician(int techId) {
        techDao.approveTechnician(techId);
        UserData tech = techDao.getTechnicianById(techId);
        if (tech != null && tech.getEmail() != null) {
            try {
                new utils.TechnicianApprovedEmail(tech.getEmail(), tech.getUsername()).send();
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Technician approved!\nNotification email sent to " + tech.getEmail(),
                        "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (jakarta.mail.MessagingException ex) {
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Technician approved, but email failed to send.\n" + ex.getMessage(),
                        "Email Error", javax.swing.JOptionPane.WARNING_MESSAGE);
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
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Technician rejected.\nNotification email sent to " + tech.getEmail(),
                        "Done", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (jakarta.mail.MessagingException ex) {
                javax.swing.JOptionPane.showMessageDialog(view,
                        "Technician rejected, but email failed to send.\n" + ex.getMessage(),
                        "Email Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        }
        loadPending();
    }

    private void viewCertificate(int techId) {
        String certPath = techDao.getCertificationPathById(techId);
        new utils.CertificatePreviewDialog(view, certPath).setVisible(true);
    }

    private void loadPendingPayments() {
        List<Appointment> payments = appointmentDao.getPendingPayments();
        view.loadPendingPayments(
                payments,
                this::approvePayment,
                this::rejectPayment,
                this::viewPaymentProof);
    }

    private void approvePayment(int appointmentId) {
        appointmentDao.updateAppointmentStatus(appointmentId, "paid");
        javax.swing.JOptionPane.showMessageDialog(view,
                "Payment approved.", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        loadPendingPayments();
    }

    private void rejectPayment(int appointmentId) {
        appointmentDao.updateAppointmentStatus(appointmentId, "awaiting_payment");
        javax.swing.JOptionPane.showMessageDialog(view,
                "Payment proof rejected. Client can resubmit.", "Done", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        loadPendingPayments();
    }

    private void viewPaymentProof(int appointmentId) {
        String proofPath = appointmentDao.getPaymentProofPathById(appointmentId);
        if (proofPath == null || proofPath.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(view,
                    "No payment proof uploaded yet by the user.",
                    "Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        new utils.CertificatePreviewDialog(view, proofPath).setVisible(true);
    }

    private void loadReviews() {
        List<model.Review> reviews = reviewDao.getAllReviews();
        view.loadReviews(reviews, this::deleteReview);
    }

    private void deleteReview(int reviewId) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to remove this review?",
                "Remove Review", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            boolean success = reviewDao.deleteReview(reviewId);
            if (success) {
                javax.swing.JOptionPane.showMessageDialog(view, "Review removed successfully.", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Failed to remove review.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            loadReviews();
        }
    }

    private void handleLogout() {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            utils.Session.clear();
            view.dispose();
            HomePage homeView = new HomePage();
            new HomeController(homeView);
            homeView.setVisible(true);
        }
    }
}