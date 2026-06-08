package controller;

import view.NotificationPage;

public class NotificationController {
    private final NotificationPage view;
    private final int userId;
    private final Runnable onBack;

    public NotificationController(NotificationPage view, int userId, Runnable onBack) {
        this.view = view;
        this.userId = userId;
        this.onBack = onBack;
        wireButtons();
    }

    private void wireButtons() {
        view.addBackListener(e -> {
            view.dispose();
            onBack.run();
        });
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}