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
                String icon = getStatusIcon(a.getStatus());
                String type = icon + "  " + a.getServiceType();
                String details = "📍 " + a.getAddress() + "   🕐 " + a.getTime();
                String date = "📅 " + a.getDate();
                notifications.add(new String[]{type, details, date});
            }
        } else if ("technician".equals(role)) {
            String specialization = Session.getSpecialization();
            List<Appointment> jobs = appointmentDao.getPendingAppointmentsByService(specialization);
            for (Appointment a : jobs) {
                String type = "🔔  New " + a.getServiceType() + " Request";
                String details = "📍 " + a.getAddress() + "   🕐 " + a.getTime();
                String date = "📅 " + a.getDate();
                notifications.add(new String[]{type, details, date});
            }
        }

        view.loadNotifications(notifications);
    }

    private String getStatusIcon(String status) {
        switch (status) {
            case "pending":   return "⏳";
            case "accepted":  return "✅";
            case "completed": return "🎉";
            case "paid":      return "💰";
            default:          return "🔔";
        }
    }

    private void startPolling() {
        pollingTimer = new Timer(5000, e -> loadNotifications());
        pollingTimer.start();
    }

    private void wireButtons() {
        view.addBackListener(e -> {
            pollingTimer.stop();
            view.dispose();
            onBack.run();
        });
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}