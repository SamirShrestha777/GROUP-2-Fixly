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
        view.loadHistory(appointments);
    }

    private void wireButtons() {
        view.addBackListener(e -> {
            view.dispose();
            onBack.run();
        });
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}