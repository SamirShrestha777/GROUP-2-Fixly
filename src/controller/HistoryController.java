package controller;

import dao.AppointmentDao;
import model.Appointment;
import view.HistoryPage;
import java.util.List;

public class HistoryController {
    private final HistoryPage view;
    private final int userId;
    private final Runnable onBack;
    private final AppointmentDao appointmentDao;
    private final dao.ReviewDao reviewDao = new dao.ReviewDao();

    public HistoryController(HistoryPage view, int userId, Runnable onBack) {
        this.view         = view;
        this.userId       = userId;
        this.onBack       = onBack;
        this.appointmentDao = new AppointmentDao();
        view.initHistoryPanel();
        loadHistory();
        wireButtons();
    }

    private void loadHistory() {
        List<Appointment> appointments = appointmentDao.getAppointmentsByUser(userId);
        view.loadHistory(appointments, this::handlePaymentConfirmation, this::handleReview, a -> {
            boolean isCompleted = "completed".equalsIgnoreCase(a.getStatus());
            return isCompleted && !reviewDao.hasReviewed(a.getId(), userId);
        });
    }

    private void handleReview(Appointment appointment) {
        // technician_id must exist on the appointment (set when technician accepted)
        int technicianId = appointment.getTechnicianId();
        if (technicianId <= 0) {
            javax.swing.JOptionPane.showMessageDialog(view,
                "No technician is assigned to this job yet.", "Info",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        utils.ReviewDialog dialog = new utils.ReviewDialog(view, appointment.getId(), userId, technicianId);
        dialog.setVisible(true);
        // Reload history after the dialog is closed to potentially hide the button
        loadHistory();
    }

    private void handlePaymentConfirmation(Appointment appointment) {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setDialogTitle("Select Payment Screenshot");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png"));

        int result = chooser.showOpenDialog(view);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            appointmentDao.submitPaymentProof(appointment.getId(), path);
            loadHistory();
        }
    }

    private void wireButtons() {
        view.addLogoutListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                utils.Session.clear();
                view.dispose();
                view.HomePage homeView = new view.HomePage();
                new HomeController(homeView);
                homeView.setVisible(true);
            }
        });
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}