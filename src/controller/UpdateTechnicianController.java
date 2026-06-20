package controller;

import utils.Session;
import view.updateTechnician;
import view.HomePage;

public class UpdateTechnicianController {
    private final updateTechnician view;
    private final TechnicianNavigationManager nav;

    public UpdateTechnicianController(updateTechnician view, TechnicianNavigationManager nav) {
        this.view = view;
        this.nav = nav;
        wireButtons();
    }

    private void wireButtons() {
        // Nav buttons are wired by TechnicianNavigationManager.wireProfileNavButtons()
        
        view.addLogoutListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                Session.clear();
                view.dispose();
                HomePage homeView = new HomePage();
                new HomeController(homeView);
                homeView.setVisible(true);
            }
        });
        
        view.addUpdateListener(e -> {
            String newUsername = view.getUsernameText();
            String newEmail = view.getEmailText();
            String newPassword = view.getPasswordText();
            String newStatus = view.getStatus(); // "Active" or "Inactive"

            if (newUsername.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Fields cannot be empty", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            dao.TechnicianDao techDao = new dao.TechnicianDao();
            int currentTechId = Session.getUserId();
            
            boolean profileUpdated = techDao.updateProfile(currentTechId, newUsername, newEmail, newPassword);
            boolean statusUpdated = techDao.updateAccountStatus(currentTechId, newStatus);

            if (profileUpdated || statusUpdated) {
                javax.swing.JOptionPane.showMessageDialog(view, "Profile updated successfully!", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Failed to update profile.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
