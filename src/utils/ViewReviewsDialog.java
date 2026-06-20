package utils;

import dao.ReviewDao;
import model.Review;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * ViewReviewsDialog — a popup to view all reviews for a specific technician.
 * Displays average rating and a scrollable list of reviews.
 */
public class ViewReviewsDialog extends JDialog {

    private final int technicianId;
    private final String technicianName;
    private final ReviewDao reviewDao = new ReviewDao();

    public ViewReviewsDialog(Frame parent, int technicianId, String technicianName) {
        super(parent, "Reviews for " + technicianName, true);
        this.technicianId = technicianId;
        this.technicianName = technicianName;
        buildUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void buildUI() {
        Color bg = new Color(30, 41, 59);
        Color cardColor = new Color(51, 65, 85);
        Font heading = new Font("Segoe UI", Font.BOLD, 18);

        getContentPane().setBackground(bg);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(500, 450));
        setResizable(false);

        // Header
        JPanel topPanel = new JPanel();
        topPanel.setBackground(bg);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));

        double avgRating = reviewDao.getAverageRating(technicianId);
        JLabel titleLabel = new JLabel("⭐ Reviews: " + technicianName);
        titleLabel.setFont(heading);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel ratingLabel = new JLabel(String.format("Average Rating: %.1f / 5.0", avgRating));
        ratingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ratingLabel.setForeground(new Color(250, 204, 21)); // Yellow
        ratingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        topPanel.add(titleLabel);
        topPanel.add(Box.createVerticalStrut(8));
        topPanel.add(ratingLabel);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Reviews List
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(bg);

        List<Review> reviews = reviewDao.getReviewsForTechnician(technicianId);
        if (reviews.isEmpty()) {
            JLabel empty = new JLabel("No reviews yet.");
            empty.setForeground(Color.LIGHT_GRAY);
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            empty.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
            listPanel.add(empty);
        } else {
            for (Review r : reviews) {
                listPanel.add(buildReviewCard(r, cardColor));
                listPanel.add(Box.createVerticalStrut(8));
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        scrollPane.getViewport().setBackground(bg);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(bg);
        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(cardColor);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dispose());
        btnPanel.add(closeBtn);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    private JPanel buildReviewCard(Review r, Color cardColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 85, 105), 1),
                BorderFactory.createEmptyBorder(12, 16, 12, 16)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel stars = new JLabel(getStars(r.getRating()));
        stars.setForeground(new Color(250, 204, 21));
        stars.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel date = new JLabel("Posted: " + (r.getCreatedAt() != null ? r.getCreatedAt().substring(0, 10) : "N/A"));
        date.setForeground(new Color(148, 163, 184));
        date.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        JTextArea comment = new JTextArea(r.getComment() != null && !r.getComment().trim().isEmpty() ? r.getComment() : "(No comment)");
        comment.setEditable(false);
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        comment.setBackground(cardColor);
        comment.setForeground(Color.WHITE);
        comment.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        card.add(stars);
        card.add(Box.createVerticalStrut(4));
        card.add(comment);
        card.add(Box.createVerticalStrut(8));
        card.add(date);

        return card;
    }

    private String getStars(int rating) {
        return "★".repeat(Math.max(0, rating)) + "☆".repeat(Math.max(0, 5 - rating));
    }
}
