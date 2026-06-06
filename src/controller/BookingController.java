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

    public BookingController(Bookingpannel view) {
        this.view = view;
        this.dao  = new AppointmentDao();
        view.addConfirmListener(e -> handleBooking());
    }

    private void handleBooking() {
        try {
            Date selectedDate  = view.getSelectedDate();
            String time        = view.getSelectedTime();
            String notes       = view.getNotes();
            String address     = view.getAddress();
            String serviceType = view.getServiceType();

            // validation
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(view, "Please select a date.");
                return;
            }
            if (serviceType == null || serviceType.equals("Select Service Type")) {
                JOptionPane.showMessageDialog(view, "Please select a service type.");
                return;
            }
            if (address.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your address.");
                return;
            }
            if (time == null || time.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please select a time.");
                return;
            }

            // format date to string for DB
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

            // build appointment
            Appointment appointment = new Appointment();
            appointment.setClientId(Session.getUserId());
            appointment.setServiceType(serviceType);
            appointment.setDate(formattedDate);
            appointment.setTime(time);
            appointment.setNotes(notes);
            appointment.setAddress(address);
            appointment.setStatus("pending");

            boolean success = dao.saveAppointment(appointment);

            if (success) {
                JOptionPane.showMessageDialog(view,
                    "Booking request sent!\n" +
                    "Service: "  + serviceType   + "\n" +
                    "Date: "     + formattedDate  + "\n" +
                    "Time: "     + time           + "\n" +
                    "A technician will accept your request shortly."
                );
                view.dispose();
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