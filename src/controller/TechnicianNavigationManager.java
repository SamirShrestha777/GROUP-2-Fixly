package controller;

import utils.Session;
import view.TechNoti;
import view.TechnicianDashboard;
import view.updateTechnician;

public class TechnicianNavigationManager {
    private final TechnicianDashboard dashboard;

    public TechnicianNavigationManager(TechnicianDashboard dashboard) {
        this.dashboard = dashboard;
    }

    // Go back to the main dashboard
    public void goToDashboard() {
        dashboard.setVisible(true);
    }

    // Navigate to Notifications page
    public void goToNotifications(java.awt.Window current) {
        current.setVisible(false);
        TechNoti notiView = new TechNoti();
        new TechNotiController(notiView, Session.getUserId(), () -> {
            notiView.dispose();
            goToDashboard();
        });
        wireNotiNavButtons(notiView);
        notiView.setLocationRelativeTo(null);
        notiView.setVisible(true);
    }

    private void wireNotiNavButtons(TechNoti notiView) {
        notiView.addHistoryNavListener(e -> { notiView.dispose(); goToDashboard(); });
        notiView.addBookingNavListener(e -> { notiView.dispose(); goToDashboard(); });
        notiView.addNotificationNavListener(e -> { /* already here */ });
        notiView.addProfileNavListener(e -> { notiView.dispose(); goToProfile(dashboard); });
        notiView.addBackListener(e -> { notiView.dispose(); goToDashboard(); });
    }

    // Navigate to Profile / Update page
    public void goToProfile(java.awt.Window current) {
        current.setVisible(false);
        updateTechnician profileView = new updateTechnician();
        new UpdateTechnicianController(profileView, this);
        wireProfileNavButtons(profileView);
        profileView.setLocationRelativeTo(null);
        profileView.setVisible(true);
    }

    private void wireProfileNavButtons(updateTechnician profileView) {
        profileView.addHistoryNavListener(e -> { profileView.dispose(); goToDashboard(); });
        profileView.addBookingNavListener(e -> { profileView.dispose(); goToDashboard(); });
        profileView.addNotificationNavListener(e -> { profileView.dispose(); goToNotifications(dashboard); });
        profileView.addProfileNavListener(e -> { /* already here */ });
    }
}