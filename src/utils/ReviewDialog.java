package utils;

import dao.ReviewDao;

/**
 * ReviewDialog — a popup that lets a user submit a 1-5 star rating + comment
 * for a completed job. Opens from HistoryCard's "Review Technician" button.
 *
 * Usage:
 *   ReviewDialog dialog = new ReviewDialog(parentFrame, appointmentId, clientId, technicianId);
 *   dialog.setVisible(true);
 */
public class ReviewDialog extends javax.swing.JDialog {

    private final int appointmentId;
    private final int clientId;
    private final int technicianId;
    private final ReviewDao reviewDao = new ReviewDao();

    private javax.swing.JSpinner ratingSpinner;
    private javax.swing.JTextArea commentArea;
    private javax.swing.JButton submitBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel commentLabel;

    public ReviewDialog(java.awt.Frame parent, int appointmentId, int clientId, int technicianId) {
        super(parent, "Rate Your Technician", true);
        this.appointmentId  = appointmentId;
        this.clientId       = clientId;
        this.technicianId   = technicianId;
        buildUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void buildUI() {
        java.awt.Color bg      = new java.awt.Color(30, 41, 59);
        java.awt.Color card    = new java.awt.Color(51, 65, 85);
        java.awt.Color accent  = new java.awt.Color(59, 130, 246);
        java.awt.Font  heading = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18);
        java.awt.Font  body    = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);

        getContentPane().setBackground(bg);
        getContentPane().setLayout(new java.awt.BorderLayout(0, 0));

        // ── Title bar ─────────────────────────────────────────────
        javax.swing.JPanel topPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 16));
        topPanel.setBackground(bg);
        titleLabel = new javax.swing.JLabel("⭐ Review Your Technician");
        titleLabel.setFont(heading);
        titleLabel.setForeground(java.awt.Color.WHITE);
        topPanel.add(titleLabel);
        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        // ── Center form ───────────────────────────────────────────
        javax.swing.JPanel formPanel = new javax.swing.JPanel();
        formPanel.setBackground(card);
        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));
        formPanel.setLayout(new javax.swing.BoxLayout(formPanel, javax.swing.BoxLayout.Y_AXIS));

        // Rating row
        ratingLabel = new javax.swing.JLabel("Rating (1 = Poor, 5 = Excellent):");
        ratingLabel.setForeground(java.awt.Color.WHITE);
        ratingLabel.setFont(body);
        ratingLabel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        formPanel.add(ratingLabel);
        formPanel.add(javax.swing.Box.createVerticalStrut(6));

        ratingSpinner = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(5, 1, 5, 1));
        ratingSpinner.setFont(body);
        ratingSpinner.setMaximumSize(new java.awt.Dimension(80, 36));
        ratingSpinner.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        formPanel.add(ratingSpinner);

        formPanel.add(javax.swing.Box.createVerticalStrut(4));

        formPanel.add(javax.swing.Box.createVerticalStrut(16));

        // Comment row
        commentLabel = new javax.swing.JLabel("Comment (optional):");
        commentLabel.setForeground(java.awt.Color.WHITE);
        commentLabel.setFont(body);
        commentLabel.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        formPanel.add(commentLabel);
        formPanel.add(javax.swing.Box.createVerticalStrut(6));

        commentArea = new javax.swing.JTextArea(4, 28);
        commentArea.setFont(body);
        commentArea.setBackground(new java.awt.Color(30, 41, 59));
        commentArea.setForeground(java.awt.Color.WHITE);
        commentArea.setCaretColor(java.awt.Color.WHITE);
        commentArea.setBorder(javax.swing.BorderFactory.createLineBorder(accent));
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        javax.swing.JScrollPane commentScroll = new javax.swing.JScrollPane(commentArea);
        commentScroll.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        commentScroll.setBorder(null);
        formPanel.add(commentScroll);

        getContentPane().add(formPanel, java.awt.BorderLayout.CENTER);

        // ── Buttons ───────────────────────────────────────────────
        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 16, 12));
        btnPanel.setBackground(bg);

        submitBtn = new javax.swing.JButton("Submit Review");
        submitBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        submitBtn.setBackground(accent);
        submitBtn.setForeground(java.awt.Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setBorderPainted(false);
        submitBtn.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        submitBtn.addActionListener(e -> handleSubmit());

        cancelBtn = new javax.swing.JButton("Cancel");
        cancelBtn.setFont(body);
        cancelBtn.setBackground(card);
        cancelBtn.setForeground(java.awt.Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(submitBtn);
        btnPanel.add(cancelBtn);
        getContentPane().add(btnPanel, java.awt.BorderLayout.SOUTH);

        setPreferredSize(new java.awt.Dimension(440, 380));
        setResizable(false);
    }

    private void handleSubmit() {
        // Guard: already reviewed?
        if (reviewDao.hasReviewed(appointmentId, clientId)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "You have already reviewed this job.", "Already Reviewed",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        int rating    = (int) ratingSpinner.getValue();
        String comment = commentArea.getText().trim();

        boolean ok = reviewDao.submitReview(appointmentId, clientId, technicianId, rating, comment);
        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Thank you! Your review has been submitted. " + getStars(rating),
                "Review Submitted", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Failed to submit review. Please try again.",
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getStars(int rating) {
        return "★".repeat(rating) + "☆".repeat(5 - rating);
    }
}
