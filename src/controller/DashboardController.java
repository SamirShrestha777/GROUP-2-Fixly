package controller;

import view.Dashboard;
import view.Bookingpannel;
import view.HistoryPage;
import view.NotificationPage;
import view.Update;
import view.HomePage;
import utils.Session;
import javax.swing.JOptionPane;

public class DashboardController {
    private final Dashboard view;

    public DashboardController(Dashboard view) {
        this.view = view;
        wireAllButtons();
    }

    // reusable go-back action
    private void goBackToDashboard() {
        view.setVisible(true);
    }

    private void wireAllButtons() {

        view.addHistoryListener(e -> {
            view.setVisible(false);
            HistoryPage historyPage = new HistoryPage();
            HistoryController historyController = new HistoryController(
                historyPage,
                Session.getUserId(),
                this::goBackToDashboard  // pass callback
            );
            historyController.open();
        });

        view.addNotificationListener(e -> {
            view.setVisible(false);
            NotificationPage notifPage = new NotificationPage();
            NotificationController notifController = new NotificationController(
                notifPage,
                Session.getUserId(),
                this::goBackToDashboard
            );
            notifController.open();
        });

        view.addProfileListener(e -> {
            view.setVisible(false);
            Update profilePage = new Update();
            UpdateController updateController = new UpdateController(
                profilePage,
                Session.getUserId(),
                this::goBackToDashboard
            );
            updateController.open();
        });

        view.addBookingListener(e -> openBooking(""));
        view.addPlumbingListener(e ->   openBooking("Plumbing"));
        view.addElectricalListener(e -> openBooking("Electrical"));
        view.addCleaningListener(e ->   openBooking("Cleaning"));
        view.addCarpentryListener(e ->  openBooking("Carpentry"));
        view.addPaintingListener(e ->   openBooking("Painting"));
        view.addACRepairListener(e ->   openBooking("AC Repair"));

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

    private void openBooking(String serviceType) {
        view.setVisible(false);
        Bookingpannel bookingView = new Bookingpannel();
        BookingController bookingController = new BookingController(
            bookingView,
            Session.getUserId(),
            serviceType,
            this::goBackToDashboard
        );
        bookingController.open();
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}