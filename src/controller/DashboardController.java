package controller;

import view.Dashboard;
import view.Bookingpannel;

public class DashboardController {

    private Dashboard view;

    public DashboardController(Dashboard view) {
        this.view = view;

        view.addBookingListener(new BookingListener());
    }

    class BookingListener implements java.awt.event.ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            Bookingpannel bookingView = new Bookingpannel();
            BookingController bookingController =
                    new BookingController(bookingView);

            bookingController.open();

            view.dispose(); // optional
        }
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}