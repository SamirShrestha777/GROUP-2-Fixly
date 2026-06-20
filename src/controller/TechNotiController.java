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
    private Timer pollingTimer;

    public TechNotiController(TechNoti view, int technicianId, Runnable onBack) {
        this.view           = view;
        this.onBack         = onBack;
        this.appointmentDao = new AppointmentDao();

        view.initNotificationPanel();
        loadNotifications();
        startPolling();
        wireButtons();
    }

    private void loadNotifications() {
        String specialization = Session.getSpecialization();
        List<Appointment> jobs = appointmentDao.getPendingAppointmentsByService(specialization);
        List<String[]> notifications = new ArrayList<>();

        for (Appointment a : jobs) {
            String type = "🔔  New " + a.getServiceType() + " Request";
            String details = "📍 " + a.getAddress() + "   🕐 " + a.getTime();
            String date = "📅 " + a.getDate();
            notifications.add(new String[]{type, details, date, a.getStatus()});
        }

        view.loadNotifications(notifications);
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
}