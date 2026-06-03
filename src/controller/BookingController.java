package controller;

import dao.AppointmentDao;
import model.Appointment;
import view.Bookingpannel;

import javax.swing.JOptionPane;
import java.util.Date;

public class BookingController {

    private Bookingpannel view;
    private AppointmentDao dao;

    public BookingController(Bookingpannel view) {
        this.view = view;
        this.dao = new AppointmentDao();

        view.addConfirmListener(new ConfirmListener());
    }

    class ConfirmListener implements java.awt.event.ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            try {

                Date appointmentDate = view.getSelectedDate();
                String time = view.getTime();
                String notes = view.getNotes();
                String address = view.getAddress();

                if (appointmentDate == null) {
                    JOptionPane.showMessageDialog(
                            view,
                            "Please select a date."
                    );
                    return;
                }

                if (time.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            view,
                            "Please fill all required fields."
                    );
                    return;
                }

                Appointment appointment = new Appointment();

                appointment.setAppointmentDate(appointmentDate);
                appointment.setTime(time);
                appointment.setNotes(notes);
                appointment.setAddress(address);

                boolean success = dao.saveAppointment(appointment);

                if (success) {

                    JOptionPane.showMessageDialog(
                            view,
                            "Booking Confirmed!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            view,
                            "Booking Failed."
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        view,
                        "Error: " + ex.getMessage()
                );

                ex.printStackTrace();
            }
        }
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}