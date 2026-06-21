package controller;

import dao.AppointmentDao;
import model.Appointment;
import view.NotificationPage;
import utils.Session;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class NotificationController {
    private final NotificationPage view;
    private final int userId;
    private final Runnable onBack;
    private final AppointmentDao appointmentDao;
    private Timer pollingTimer;
    private String currentFilter = "All";

    public NotificationController(NotificationPage view, int userId, Runnable onBack) {
        this.view           = view;
        this.userId         = userId;
        this.onBack         = onBack;
        this.appointmentDao = new AppointmentDao();

        view.initNotificationPanel();
        loadNotifications();
        startPolling();
        wireButtons();
    }

    private void loadNotifications() {
        String role = Session.getRole();
        List<String[]> notifications = new ArrayList<>();

        if ("client".equals(role)) {
            List<Appointment> appointments = appointmentDao.getAppointmentsByUser(userId);
            for (Appointment a : appointments) {
                if (!matchesFilter(a.getStatus())) continue;
                String type = a.getServiceType() + " (" + a.getStatus().toUpperCase() + ")";
                String details = a.getAddress() + "   " + a.getTime();
                String date = a.getDate();
                notifications.add(new String[]{type, details, date, a.getStatus()});
            }
        } else if ("technician".equals(role)) {
            String specialization = Session.getSpecialization();
            int techId = Session.getUserId();
            // Fetch ALL statuses so Approved/Declined filters can work
            List<Appointment> jobs = appointmentDao.getAllAppointmentsByServiceForTech(specialization, techId);
            for (Appointment a : jobs) {
                if (!matchesFilter(a.getStatus())) continue;
                String type = "New " + a.getServiceType() + " Request (" + a.getStatus().toUpperCase() + ")";
                String details = a.getAddress() + "   " + a.getTime();
                String date = a.getDate();
                notifications.add(new String[]{type, details, date, a.getStatus()});
            }
        }

        view.loadNotifications(notifications);
    }

    private boolean matchesFilter(String status) {
        if ("All".equals(currentFilter)) return true;
        return currentFilter.equalsIgnoreCase(status);
    }

    private void startPolling() {
        pollingTimer = new Timer(5000, e -> loadNotifications());
        pollingTimer.start();
    }

    private void wireButtons() {
        view.addLogoutListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                if (pollingTimer != null) pollingTimer.stop();
                Session.clear();
                view.dispose();
                view.HomePage homeView = new view.HomePage();
                new HomeController(homeView);
                homeView.setVisible(true);
            }
        });

        view.addAllFilterListener(e -> { currentFilter = "All"; loadNotifications(); });
        view.addCompletedFilterListener(e -> { currentFilter = "completed"; loadNotifications(); });
        view.addDeclinedFilterListener(e -> { currentFilter = "declined"; loadNotifications(); });
        view.addPendingFilterListener(e -> { currentFilter = "pending"; loadNotifications(); });
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}