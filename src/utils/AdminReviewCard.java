package utils;

import javax.swing.*;
import java.awt.*;

public class AdminReviewCard extends JPanel {

    private JLabel clientNameLabel;
    private JLabel techNameLabel;
    private JLabel ratingLabel;
    private JLabel commentLabel;
    private JButton deleteBtn;

    public AdminReviewCard() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(30, 41, 59));
        setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85), 1, true));
        setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setOpaque(false);

        clientNameLabel = new JLabel("Client: ");
        clientNameLabel.setForeground(Color.WHITE);
        clientNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        techNameLabel = new JLabel("Technician: ");
        techNameLabel.setForeground(Color.LIGHT_GRAY);

        ratingLabel = new JLabel("Rating: ");
        ratingLabel.setForeground(new Color(255, 204, 0)); // Gold

        commentLabel = new JLabel("Comment: ");
        commentLabel.setForeground(Color.WHITE);

        infoPanel.add(clientNameLabel);
        infoPanel.add(techNameLabel);
        infoPanel.add(ratingLabel);
        infoPanel.add(commentLabel);

        add(infoPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);

        deleteBtn = new JButton("Remove Review");
        deleteBtn.setBackground(new Color(220, 53, 69)); // Danger red
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        btnPanel.add(deleteBtn);
        add(btnPanel, BorderLayout.EAST);
        
        // Add padding
        setBorder(BorderFactory.createCompoundBorder(
            getBorder(),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
    }

    public void setCardData(String client, String tech, int rating, String comment) {
        clientNameLabel.setText("Client: " + client);
        techNameLabel.setText("Technician: " + tech);
        
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) stars.append("★");
        for (int i = rating; i < 5; i++) stars.append("☆");
        ratingLabel.setText("Rating: " + stars.toString());
        
        commentLabel.setText("Comment: " + comment);
    }

    public void addDeleteListener(java.awt.event.ActionListener l) {
        deleteBtn.addActionListener(l);
    }
}
