package controller;

import view.HistoryPage;

public class HistoryController {
    private final HistoryPage view;
    private final int userId;
    private final Runnable onBack;

    public HistoryController(HistoryPage view, int userId, Runnable onBack) {
        this.view = view;
        this.userId = userId;
        this.onBack = onBack;
        wireButtons();
        loadHistory();
    }

    private void wireButtons() {
        view.addBackListener(e -> {
            view.dispose();
            onBack.run(); // calls goBackToDashboard()
        });
    }

    private void loadHistory() {
        // load data from DB here
    }

    public void open() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}