package controller;

import view.Dashboard;
import view.HomePage;
import utils.Session;
import javax.swing.JOptionPane;

public class DashboardController {
    private final Dashboard view;
    private final NavigationManager nav;

    public DashboardController(Dashboard view) {
        this.view = view;
        this.nav  = new NavigationManager(view);
        wireAllButtons();
    }

    private void wireAllButtons() {
        view.addHistoryListener(e -> nav.goToHistory(view));
        view.addNotificationListener(e -> nav.goToNotification(view));
        view.addProfileListener(e -> nav.goToProfile(view));
        view.addBookingListener(e -> nav.goToBooking(view, ""));

        view.addPlumbingListener(e ->   nav.goToBooking(view, "Plumbing"));
        view.addElectricalListener(e -> nav.goToBooking(view, "Electrical"));
        view.addCleaningListener(e ->   nav.goToBooking(view, "Cleaning"));
        view.addCarpentryListener(e ->  nav.goToBooking(view, "Carpentry"));
        view.addPaintingListener(e ->   nav.goToBooking(view, "Painting"));
        view.addACRepairListener(e ->   nav.goToBooking(view, "AC Repair"));

        view.addLogoutListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
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