package controller;

import dao.TechnicianDao;
import dao.AppointmentDao;
import model.UserData;
import model.Appointment;
import view.AdminDashboard;
import view.HomePage;
import java.util.List;

public class AdminController {
    private final AdminDashboard view;
    private final TechnicianDao techDao = new TechnicianDao();
    private final AppointmentDao appointmentDao = new AppointmentDao();

    public AdminController(AdminDashboard view) {
        this.view = view;
        view.initPendingPanel();
        view.initMonitorPanel();
        view.addLogoutListener(e -> handleLogout());
        view.addVerifyNavListener(e -> { view.showVerifyPanel(); loadPending(); });
        view.addMonitorNavListener(e -> { view.showMonitorPanel(); loadPendingPayments(); });
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
        new view.CertificatePreviewDialog(view, certPath).setVisible(true);
    }

    private void loadPendingPayments() {
        List<Appointment> payments = appointmentDao.getAppointmentsByStatus("payment_submitted");
        view.loadPendingPayments(
            payments,
            this::approvePayment,
            this::rejectPayment,
            this::viewPaymentProof
        );
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
        new view.CertificatePreviewDialog(view, proofPath).setVisible(true);
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