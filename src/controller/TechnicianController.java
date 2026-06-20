package controller;

import dao.AppointmentDao;
import dao.TechnicianDao;
import model.Appointment;
import view.TechnicianDashboard;
import view.HomePage;
import utils.Session;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.Timer;

public class TechnicianController {
    private final TechnicianDashboard view;
    private final AppointmentDao appointmentDao;
    private final TechnicianDao technicianDao;
    private final TechnicianNavigationManager nav;
    private Timer pollingTimer;

    public TechnicianController(TechnicianDashboard view) {
        this.view = view;
        this.appointmentDao = new AppointmentDao();
        this.technicianDao = new TechnicianDao();
        this.nav = new TechnicianNavigationManager(view);

        // Init both panels in the view
        view.initRequestsPanel();
        view.initHistoryPanel();

        view.hideFilterButtons();

        // Auto-show Requests panel on open
        loadPendingRequests();
        view.showRequestPanel();

        startPolling();
        wireNavButtons();
        wireLogout();
    }

    private String currentFilter = "All";

    // ── Data Loaders ────────────────────────────────────────────────────────
    private void loadPendingRequests() {
        int techId = Session.getUserId();
        model.UserData tech = technicianDao.getTechnicianById(techId);
        String spec = (tech != null) ? tech.getSpecialization() : null;

        java.util.List<Appointment> appointments;
        if (spec != null && !spec.trim().isEmpty()) {
            appointments = appointmentDao.getPendingAndAcceptedByService(spec, techId);
        } else {
            appointments = appointmentDao.getPendingAndAcceptedAppointments(techId);
        }
        view.loadAppointments(appointments, this::acceptRequest, this::rejectRequest, this::completeRequest);
    }

    private void acceptRequest(int appointmentId) {
        int techId = Session.getUserId();
        if (technicianDao.acceptJob(appointmentId, techId)) {
            javax.swing.JOptionPane.showMessageDialog(view, "Request Accepted! ✅", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Failed to accept request.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        loadPendingRequests();
    }

    private void rejectRequest(int appointmentId) {
        int techId = Session.getUserId();
        if (technicianDao.declineJob(appointmentId, techId)) {
            javax.swing.JOptionPane.showMessageDialog(view, "Request Declined.", "Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Failed to decline request.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        loadPendingRequests();
    }

    private void completeRequest(int appointmentId) {
        int techId = Session.getUserId();
        if (technicianDao.completeJob(appointmentId, techId)) {
            javax.swing.JOptionPane.showMessageDialog(view, "Job marked as Completed! 🎉", "Done", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(view, "Failed to mark complete. Try again.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        loadPendingRequests();
    }

    private void loadJobHistory() {
        int techId = Session.getUserId();
        List<Appointment> jobs = technicianDao.getJobHistory(techId);
        view.loadHistory(jobs);
    }

    // ── Polling ─────────────────────────────────────────────────────────────
    private void startPolling() {
        pollingTimer = new Timer(5000, e -> loadPendingRequests());
        pollingTimer.start();
    }

    // ── Nav Buttons ─────────────────────────────────────────────────────────
    private void wireNavButtons() {
        // "Request" button → show requests panel
        view.addRequestNavListener(e -> {
            loadPendingRequests();
            view.showRequestPanel();
        });

        // "History" button → load and show history panel
        view.addHistoryNavListener(e -> {
            loadJobHistory();
            view.showHistoryPanel();
        });

        // "Notification" button → navigate to TechNoti page
        view.addNotificationNavListener(e -> nav.goToNotifications(view));

        // "Profile" button → navigate to updateTechnician page
        view.addProfileNavListener(e -> {
            pollingTimer.stop();
            nav.goToProfile(view);
        });
        
        // Filter Buttons — strings must match EXACTLY what is stored in the DB service_type column
        view.addFilterAllListener(e ->       { currentFilter = "All";        loadPendingRequests(); });
        view.addFilterElectricalListener(e -> { currentFilter = "Electrician"; loadPendingRequests(); });
        view.addFilterPlumbingListener(e ->   { currentFilter = "Plumber";     loadPendingRequests(); });
        view.addFilterAcListener(e ->         { currentFilter = "AC Repair";   loadPendingRequests(); });
        view.addFilterPainterListener(e ->    { currentFilter = "Painting";    loadPendingRequests(); });
        view.addFilterCarpenterListener(e ->  { currentFilter = "Carpenter";   loadPendingRequests(); });
    }


    // ── Logout ──────────────────────────────────────────────────────────────
    private void wireLogout() {
        view.addLogoutListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                pollingTimer.stop();
                Session.clear();
                view.dispose();
                HomePage homeView = new HomePage();
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