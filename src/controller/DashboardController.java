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

        // 6 service buttons → open specific technician page with filter
        view.addPlumbingListener(e ->   nav.goToSpecificTechnician(view, "Plumber"));
        view.addElectricalListener(e -> nav.goToSpecificTechnician(view, "Electrician"));
        view.addCleaningListener(e ->   nav.goToSpecificTechnician(view, "Cleaning"));
        view.addCarpentryListener(e ->  nav.goToSpecificTechnician(view, "Carpenter"));
        view.addPaintingListener(e ->   nav.goToSpecificTechnician(view, "Painting"));
        view.addACRepairListener(e ->   nav.goToSpecificTechnician(view, "AC Repair"));

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