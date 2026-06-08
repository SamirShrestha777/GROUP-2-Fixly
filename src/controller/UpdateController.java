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