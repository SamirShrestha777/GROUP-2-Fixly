package controller;

import utils.Session;
import view.Bookingpannel;
import view.HistoryPage;
import view.NotificationPage;
import view.Update;
import view.Dashboard;

public class NavigationManager {
    private final Dashboard dashboard;

    public NavigationManager(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public void goToDashboard() {
        dashboard.setVisible(true);
    }

    public void goToHistory(java.awt.Window current) {
        current.setVisible(false);
        HistoryPage view = new HistoryPage();
        new HistoryController(view, Session.getUserId(), () -> {
            view.dispose();
            goToDashboard();
        });
        wireNavButtons(view);
        wireDashButton(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void goToBooking(java.awt.Window current, String service) {
        current.setVisible(false);
        Bookingpannel view = new Bookingpannel(Session.getUserId(), service);
        new BookingController(view, Session.getUserId(), service, () -> {
            view.dispose();
            goToDashboard();
        });
        wireNavButtons(view);
        wireDashButton(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void goToNotification(java.awt.Window current) {
        current.setVisible(false);
        NotificationPage view = new NotificationPage();
        new NotificationController(view, Session.getUserId(), () -> {
            view.dispose();
            goToDashboard();
        });
        wireNavButtons(view);
        wireDashButton(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void goToProfile(java.awt.Window current) {
        current.setVisible(false);
        Update view = new Update();
        new UpdateController(view, Session.getUserId(), () -> {
            view.dispose();
            goToDashboard();
        });
        wireNavButtons(view);
        wireDashButton(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void goToSpecificTechnician(java.awt.Window current, String service) {
        current.setVisible(false);
        view.SpecificTechnician view = new view.SpecificTechnician();
        new SpecificTechnicianController(view, service, () -> {
            view.dispose();
            goToDashboard();
        });
        wireNavButtons(view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    // ── nav bar buttons ──────────────────────────────────────────
    private void wireNavButtons(Bookingpannel view) {
        view.addHistoryNavListener(e -> { view.dispose(); goToHistory(dashboard); });
        view.addBookingNavListener(e -> { view.dispose(); goToBooking(dashboard, ""); });
        view.addNotificationNavListener(e -> { view.dispose(); goToNotification(dashboard); });
        view.addProfileNavListener(e -> { view.dispose(); goToProfile(dashboard); });
    }

    private void wireNavButtons(HistoryPage view) {
        view.addHistoryNavListener(e -> { view.dispose(); goToHistory(dashboard); });
        view.addBookingNavListener(e -> { view.dispose(); goToBooking(dashboard, ""); });
        view.addNotificationNavListener(e -> { view.dispose(); goToNotification(dashboard); });
        view.addProfileNavListener(e -> { view.dispose(); goToProfile(dashboard); });
    }

    private void wireNavButtons(NotificationPage view) {
        view.addHistoryNavListener(e -> { view.dispose(); goToHistory(dashboard); });
        view.addBookingNavListener(e -> { view.dispose(); goToBooking(dashboard, ""); });
        view.addNotificationNavListener(e -> { view.dispose(); goToNotification(dashboard); });
        view.addProfileNavListener(e -> { view.dispose(); goToProfile(dashboard); });
    }

    private void wireNavButtons(Update view) {
        view.addHistoryNavListener(e -> { view.dispose(); goToHistory(dashboard); });
        view.addBookingNavListener(e -> { view.dispose(); goToBooking(dashboard, ""); });
        view.addNotificationNavListener(e -> { view.dispose(); goToNotification(dashboard); });
        view.addProfileNavListener(e -> { view.dispose(); goToProfile(dashboard); });
    }

    private void wireNavButtons(view.SpecificTechnician view) {
        view.addHistoryListener(e -> { view.dispose(); goToHistory(dashboard); });
        view.addBookingListener(e -> { view.dispose(); goToBooking(dashboard, ""); });
        view.addNotificationListener(e -> { view.dispose(); goToNotification(dashboard); });
        view.addProfileListener(e -> { view.dispose(); goToProfile(dashboard); });
    }

    // ── dashboard button ─────────────────────────────────────────
    private void wireDashButton(Bookingpannel view) {
        view.addDashboardListener(e -> { view.dispose(); goToDashboard(); });
    }

    private void wireDashButton(HistoryPage view) {
        view.addDashboardListener(e -> { view.dispose(); goToDashboard(); });
    }

    private void wireDashButton(NotificationPage view) {
        view.addDashboardListener(e -> { view.dispose(); goToDashboard(); });
    }

    private void wireDashButton(Update view) {
        view.addDashboardListener(e -> { view.dispose(); goToDashboard(); });
    }
}