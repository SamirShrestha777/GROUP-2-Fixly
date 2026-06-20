package controller;

import view.Update;

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
        view.addLogoutListener(e -> {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                view, "Are you sure you want to logout?",
                "Logout", javax.swing.JOptionPane.YES_NO_OPTION
            );
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                utils.Session.clear();
                view.dispose();
                view.HomePage homeView = new view.HomePage();
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