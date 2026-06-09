package controller;

import dao.AppointmentDao;
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
    private Timer pollingTimer;

    public TechnicianController(TechnicianDashboard view) {
        this.view = view;
        this.appointmentDao = new AppointmentDao();
        view.initRequestsPanel();
        loadPendingAppointments();
        startPolling();
        wireButtons();
    }

    private void loadPendingAppointments() {
        List<Appointment> appointments = appointmentDao.getPendingAppointments();
        view.loadAppointments(appointments);
    }

    private void startPolling() {
        pollingTimer = new Timer(5000, e -> loadPendingAppointments());
        pollingTimer.start();
    }

    private void wireButtons() {
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