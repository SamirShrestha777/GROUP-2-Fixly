package controller;

import dao.HireDao;
import dao.TechnicianDao;
import model.UserData;
import utils.Session;
import view.SpecificTechnician;

import java.util.List;

public class SpecificTechnicianController {
    private final SpecificTechnician view;
    private final String specialization;
    private final Runnable onBack;
    private final TechnicianDao technicianDao = new TechnicianDao();
    private final dao.AppointmentDao appointmentDao = new dao.AppointmentDao();
    private final dao.UserDao userDao = new dao.UserDao();
    private final int userId = Session.getUserId();

    public SpecificTechnicianController(SpecificTechnician view, String specialization, Runnable onBack) {
        this.view = view;
        this.specialization = specialization;
        this.onBack = onBack;

        view.initListPanel();
        String title = specialization == null || specialization.isEmpty() ? "All Technicians" : specialization + " Technicians";
        view.setHeaderTitle(title);

        wireButtons();
        loadTechnicians();
    }

    private void loadTechnicians() {
        List<UserData> technicians = (specialization == null || specialization.isEmpty())
                ? technicianDao.getAllVerifiedTechnicians()
                : technicianDao.getVerifiedTechniciansBySpecialization(specialization);

        view.loadTechnicians(
                technicians,
                techId -> appointmentDao.hasPendingDirectHire(userId, techId),
                this::handleHire,
                this::handleReviews
        );
    }

    private void handleHire(UserData tech) {
        String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        // Bug 4 fix: getUserById() can return null if session is stale
        UserData currentUser = userDao.getUserById(userId);
        String address = (currentUser != null && currentUser.getAddress() != null && !currentUser.getAddress().isEmpty())
                ? currentUser.getAddress() : "Address on file";

        model.Appointment app = new model.Appointment();
        app.setClientId(userId);
        app.setTechnicianId(tech.getId());
        app.setServiceType(tech.getSpecialization());
        app.setDate(today);
        app.setTime("TBD (Direct Hire)");
        app.setAddress(address);
        app.setNotes("Direct hire request from user dashboard.");
        app.setStatus("pending");
        app.setPaymentMethod("tbd");

        if (appointmentDao.saveAppointment(app)) {
            javax.swing.JOptionPane.showMessageDialog(view,
                    tech.getUsername() + " has been hired! The request is now pending in your history.",
                    "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            loadTechnicians(); // Refresh list to update button state
        } else {
            javax.swing.JOptionPane.showMessageDialog(view,
                    "Could not hire technician. Please try again.",
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleReviews(UserData tech) {
        utils.ViewReviewsDialog dialog = new utils.ViewReviewsDialog(view, tech.getId(), tech.getUsername());
        dialog.setVisible(true);
    }

    private void wireButtons() {
        view.addLogoutListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    view, "Are you sure you want to logout?",
                    "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                Session.clear();
                view.dispose();
                view.HomePage homeView = new view.HomePage();
                new HomeController(homeView);
                homeView.setVisible(true);
            }
        });
        view.addDashboardListener(e -> onBack.run());
        // For the side nav, if they exist
        // Note: other nav listeners are handled via NavigationManager
    }
}
