package controller;

import dao.AppointmentDao;
import model.Appointment;
import utils.Session;
import view.TechNoti;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class TechNotiController {
    private final TechNoti view;
    private final AppointmentDao appointmentDao;
    private final Runnable onBack;
    private final int technicianId;
    private Timer pollingTimer;
    private String currentFilter = "All";

    public TechNotiController(TechNoti view, int technicianId, Runnable onBack) {
        this.view           = view;
        this.technicianId   = technicianId;
        this.onBack         = onBack;
        this.appointmentDao = new AppointmentDao();

        view.initNotificationPanel();
        loadNotifications();
        startPolling();
        wireButtons();
    }

    private void loadNotifications() {
        String specialization = Session.getSpecialization();
        List<Appointment> jobs = appointmentDao.getAllAppointmentsByServiceForTech(specialization, technicianId);
        List<String[]> notifications = new ArrayList<>();

        for (Appointment a : jobs) {
            if (!matchesFilter(a.getStatus())) continue;

            String type = "New " + a.getServiceType() + " Request (" + a.getStatus().toUpperCase() + ")";
            String details = a.getAddress() + "   " + a.getTime();
            String date = a.getDate();
            notifications.add(new String[]{type, details, date, a.getStatus()});
        }

        view.loadNotifications(notifications);
    }

    private boolean matchesFilter(String status) {
        if ("All".equals(currentFilter)) return true;
        if ("Pending".equals(currentFilter))  return "pending".equalsIgnoreCase(status);
        if ("Approved".equals(currentFilter)) return "accepted".equalsIgnoreCase(status);
        if ("Declined".equals(currentFilter)) return "rejected".equalsIgnoreCase(status);
        return true;
    }

    private void startPolling() {
        pollingTimer = new Timer(5000, e -> loadNotifications());
        pollingTimer.start();
        // Bug 7 fix: stop the timer automatically whenever the window is closed/disposed
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) { stopTimer(); }
            @Override
            public void windowClosed(java.awt.event.WindowEvent e)  { stopTimer(); }
        });
    }

    /** Stop the background polling timer. Called by NavigationManager on navigation. */
    public void stopTimer() {
        if (pollingTimer != null && pollingTimer.isRunning()) pollingTimer.stop();
    }

    private void wireButtons() {
        view.addBackListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    view, "Are you sure you want to logout?",
                    "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                stopTimer();
                utils.Session.clear();
                for (java.awt.Window window : java.awt.Window.getWindows()) {
                    if (window != null) window.dispose();
                }
                view.HomePage homeView = new view.HomePage();
                new HomeController(homeView);
                homeView.setVisible(true);
            }
        });
        // Bug 11 fix: profile nav is handled entirely by TechnicianNavigationManager;
        // do NOT wire it here — the old listener disposed the view without navigating.
    }
}