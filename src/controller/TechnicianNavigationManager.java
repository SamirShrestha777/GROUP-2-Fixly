package controller;

import utils.Session;
import view.TechNoti;
import view.TechnicianDashboard;

public class TechnicianNavigationManager {
    private final TechnicianDashboard dashboard;

    public TechnicianNavigationManager(TechnicianDashboard dashboard) {
        this.dashboard = dashboard;
    }

    public void goToDashboard() {
        dashboard.setVisible(true);
    }

    public void goToNotifications(java.awt.Window current) {
        current.setVisible(false);
        TechNoti view = new TechNoti();
        new TechNotiController(view, Session.getUserId(), () -> {
            view.dispose();
            goToDashboard();
        });
        wireNavButtons(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void wireNavButtons(TechNoti view) {
        view.addNotificationNavListener(e -> { view.dispose(); goToNotifications(dashboard); });
        view.addBackListener(e -> { view.dispose(); goToDashboard(); });
        // History and Profile can be wired here later when those pages exist
    }
}