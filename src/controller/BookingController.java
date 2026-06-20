package controller;

import dao.AppointmentDao;
import model.Appointment;
import view.Bookingpannel;
import view.HomePage;
import utils.Session;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingController {
    private final Bookingpannel view;
    private final AppointmentDao dao;
    private final Runnable onBack;

    private static final String STRIPE_PAYMENT_LINK =
        "https://buy.stripe.com/test_9B6dRb94E5rx7PpflKbwk00";

    public BookingController(Bookingpannel view, int userId,
                             String serviceType, Runnable onBack) {
        this.view   = view;
        this.dao    = new AppointmentDao();
        this.onBack = onBack;

        if (serviceType != null && !serviceType.isEmpty()) {
            view.setServiceType(serviceType);
        }

        view.addConfirmListener(e -> handleBooking());
        view.addBackListener(e -> {
            view.dispose();
            onBack.run();
        });
    }

    private void handleBooking() {
        try {
            Date selectedDate  = view.getSelectedDate();
            String time        = view.getSelectedTime();
            String notes       = view.getNotes();
            String address     = view.getAddress();
            String serviceType = view.getServiceType();

            if (selectedDate == null) {
                JOptionPane.showMessageDialog(view, "Please select a date.");
                return;
            }
            if (serviceType == null || serviceType.equals("Select Service Type")) {
                JOptionPane.showMessageDialog(view, "Please select a service type.");
                return;
            }
            if (address == null || address.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your address.");
                return;
            }
            if (time == null || time.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please select a time.");
                return;
            }

            Object[] options = {"💳 Pay with Card", "💵 Pay with Cash"};
            int choice = JOptionPane.showOptionDialog(view,
                "How would you like to pay?",
                "Select Payment Method",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                return;
            }

            String paymentMethod = (choice == 0) ? "card" : "cash";
            String status = (choice == 0) ? "awaiting_payment" : "pending";

            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

            Appointment appointment = new Appointment();
            appointment.setClientId(Session.getUserId());
            appointment.setServiceType(serviceType);
            appointment.setDate(formattedDate);
            appointment.setTime(time);
            appointment.setNotes(notes);
            appointment.setAddress(address);
            appointment.setStatus(status);
            appointment.setPaymentMethod(paymentMethod);

            boolean success = dao.saveAppointment(appointment);

            if (success) {
                if (choice == 0) {
                    try {
                        java.awt.Desktop.getDesktop().browse(
                            new java.net.URI(STRIPE_PAYMENT_LINK));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "Could not open payment page.");
                    }
                    JOptionPane.showMessageDialog(view,
                        "Booking saved! Complete payment in your browser.\n" +
                        "Service: " + serviceType + "\n" +
                        "Date: "    + formattedDate + "\n" +
                        "Time: "    + time
                    );
                } else {
                    JOptionPane.showMessageDialog(view,
                        "Booking confirmed! Pay cash on service completion.\n" +
                        "Service: " + serviceType + "\n" +
                        "Date: "    + formattedDate + "\n" +
                        "Time: "    + time
                    );
                }
                view.dispose();
                onBack.run();
            } else {
                JOptionPane.showMessageDialog(view, "Booking failed. Please try again.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}