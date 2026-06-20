package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.Desktop;

public class CertificatePreviewDialog extends JDialog {

    public CertificatePreviewDialog(JFrame parent, String certPath) {
        super(parent, "Technician Certificate", true);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(new Color(30, 41, 59));
        setLayout(new BorderLayout(10, 10));

        if (certPath == null || certPath.isEmpty()) {
            add(centeredLabel("No certificate uploaded."), BorderLayout.CENTER);
            return;
        }

        File file = new File(certPath);
        String lower = certPath.toLowerCase();

        if (!file.exists()) {
            add(centeredLabel("Certificate file not found:\n" + certPath), BorderLayout.CENTER);
            return;
        }

        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png")) {
            // inline image preview
            ImageIcon icon = new ImageIcon(certPath);
            Image scaled = icon.getImage().getScaledInstance(450, -1, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaled));
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JScrollPane scrollPane = new JScrollPane(imgLabel);
            scrollPane.setBorder(null);
            add(scrollPane, BorderLayout.CENTER);

            add(buildButtonRow(file), BorderLayout.SOUTH);
        } else {
            // fallback for PDFs and other file types
            JPanel fallback = new JPanel();
            fallback.setLayout(new BoxLayout(fallback, BoxLayout.Y_AXIS));
            fallback.setBackground(new Color(30, 41, 59));
            fallback.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

            JLabel icon = centeredLabel("📄");
            icon.setFont(new Font("Segoe UI", Font.PLAIN, 48));

            JLabel nameLabel = centeredLabel(file.getName());
            nameLabel.setForeground(Color.WHITE);

            fallback.add(icon);
            fallback.add(Box.createVerticalStrut(10));
            fallback.add(nameLabel);

            add(fallback, BorderLayout.CENTER);
            add(buildButtonRow(file), BorderLayout.SOUTH);
        }
    }

    private JLabel centeredLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private JPanel buildButtonRow(File file) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        row.setBackground(new Color(30, 41, 59));

        JButton openBtn = new JButton("Open Externally");
        openBtn.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Could not open file: " + ex.getMessage());
            }
        });

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());

        row.add(openBtn);
        row.add(closeBtn);
        return row;
    }
}