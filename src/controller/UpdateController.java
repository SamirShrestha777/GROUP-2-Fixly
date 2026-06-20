package controller;

import dao.UserDao;
import utils.Session;
import view.Update;
import view.HomePage;
import view.Dashboard;

public class UpdateController {
    private final Update view;
    private final int userId;
    private final Runnable onBack;

    public UpdateController(Update view, int userId, Runnable onBack) {
        this.view = view;
        this.userId = userId;
        this.onBack = onBack;
        wireButtons();
    }

    private void wireButtons() {
        // Logout
        view.addLogoutListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                utils.Session.clear();
                view.dispose();
                HomePage homeView = new HomePage();
                new HomeController(homeView);
                homeView.setVisible(true);
            }
        });

        // Back to dashboard
        view.addDashboardListener(e -> {
            view.dispose();
            if (onBack != null) onBack.run();
        });

        // Update profile button
        view.addUpdateListener(e -> {
            String newUsername = view.getUsernameText().trim();
            String newEmail    = view.getEmailText().trim();
            String newPassword = view.getPasswordText().trim();

            if (newUsername.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view,
                    "All fields are required.", "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            UserDao userDao = new UserDao();
            boolean updated = userDao.updateProfile(userId, newUsername, newEmail, newPassword);

            if (updated) {
                // Refresh session email
                Session.setEmail(newEmail);
                javax.swing.JOptionPane.showMessageDialog(view,
                    "Profile updated successfully!", "Success",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(view,
                    "Failed to update profile. Please try again.", "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}