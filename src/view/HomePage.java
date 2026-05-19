package view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 * Responsive HomePage:
 * - Starts at 800x560
 * - When maximized, components resize to fill the window
 * - Uses FlatSVGIcon(String,int,int) and checks resource presence
 *
 * Place the SVG on the classpath at:
 * src/main/resources/view/fixly_mechanic_svg.svg
 * so it is available as "view/fixly_mechanic_svg.svg" at runtime.
 */
public class HomePage extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(HomePage.class.getName());

    // UI components that need to be resized dynamically
    private JLabel mascotLabel;
    private JTextField txtboxemail;
    private JPasswordField txtboxemail1;
    private JButton sndbutton;

    // resource path used by FlatSVGIcon(String,int,int)
    private static final String SVG_RESOURCE_PATH = "view/fixly_mechanic_svg.svg";

    public HomePage() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fixly - Login");
        setResizable(true);

        // Start window size exactly 800px width (and reasonable height)
        setSize(800, 560);
        setMinimumSize(new Dimension(600, 420));
        setLocationRelativeTo(null); // center on screen

        // Main panel: two columns
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setPreferredSize(new Dimension(800, 560));

        // LEFT PANEL (mascot)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(15, 23, 42));

        mascotLabel = new JLabel();
        mascotLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mascotLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Load SVG if available; FlatSVGIcon expects a String resource path
        if (getClass().getResource("/" + SVG_RESOURCE_PATH) != null) {
            // initial icon size: roughly half the window width minus padding
            int iconW = Math.max(300, getWidth() / 2 - 40);
            int iconH = Math.max(300, getHeight() - 160);
            mascotLabel.setIcon(new FlatSVGIcon(SVG_RESOURCE_PATH, iconW, iconH));
        } else {
            mascotLabel.setText("Mascot SVG not found");
            mascotLabel.setForeground(Color.WHITE);
        }
        leftPanel.add(mascotLabel, BorderLayout.CENTER);

        // RIGHT PANEL (login form)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(30, 41, 59));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel titleLabel = new JLabel("Welcome Back");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(248, 250, 252));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 40, 4, 40);
        rightPanel.add(titleLabel, gbc);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Login into your account");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(148, 163, 184));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 40, 24, 40);
        rightPanel.add(subtitleLabel, gbc);

        // Email
        txtboxemail = new JTextField();
        txtboxemail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtboxemail.putClientProperty("JTextField.placeholderText", "Enter your email address");
        txtboxemail.setPreferredSize(new Dimension(320, 38));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 40, 12, 40);
        rightPanel.add(txtboxemail, gbc);

        // Password
        txtboxemail1 = new JPasswordField();
        txtboxemail1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtboxemail1.putClientProperty("JTextField.placeholderText", "Enter your password");
        txtboxemail1.setPreferredSize(new Dimension(320, 38));
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 40, 20, 40);
        rightPanel.add(txtboxemail1, gbc);

        // Login button
        sndbutton = new JButton("Login");
        sndbutton.setBackground(new Color(29, 78, 216));
        sndbutton.setForeground(Color.WHITE);
        sndbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sndbutton.setPreferredSize(new Dimension(320, 40));
        sndbutton.setBorderPainted(false);
        sndbutton.setFocusPainted(false);
        sndbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sndbutton.addActionListener(this::sndbuttonActionPerformed);
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 40, 20, 40);
        rightPanel.add(sndbutton, gbc);

        // Assemble
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // React to maximize/restore and general resize:
        // 1) WindowStateListener detects maximize button (MAXIMIZED_BOTH)
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                boolean maximized = (e.getNewState() & Frame.MAXIMIZED_BOTH) != 0;
                adjustLayoutForSize(maximized ? getGraphicsConfiguration().getBounds().width
                        : getWidth(),
                        maximized ? getGraphicsConfiguration().getBounds().height
                                : getHeight());
            }
        });

        // 2) ComponentListener handles manual resizing and restore (when not using
        // maximize)
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // adjust based on current frame size
                adjustLayoutForSize(getWidth(), getHeight());
            }
        });

        pack();
        // Ensure the window uses the explicit size we want (pack may shrink)
        setSize(800, 560);
        setLocationRelativeTo(null);
    }

    /**
     * Adjusts icon and form widths based on the current frame size.
     * Called on maximize, restore, and manual resize.
     */
    private void adjustLayoutForSize(int frameWidth, int frameHeight) {
        // Left column roughly half the frame width
        int leftColumnWidth = Math.max(300, frameWidth / 2);
        int rightColumnWidth = Math.max(300, frameWidth - leftColumnWidth);

        // Resize mascot icon to fit left column with padding
        if (getClass().getResource("/" + SVG_RESOURCE_PATH) != null) {
            int iconW = Math.max(300, leftColumnWidth - 80);
            int iconH = Math.max(300, frameHeight - 160);
            // Replace icon with a new sized icon
            mascotLabel.setIcon(new FlatSVGIcon(SVG_RESOURCE_PATH, iconW, iconH));
        }

        // Resize form fields to be a percentage of the right column width
        int fieldWidth = Math.max(280, rightColumnWidth - 160);
        Dimension fieldDim = new Dimension(fieldWidth, 38);
        txtboxemail.setPreferredSize(fieldDim);
        txtboxemail1.setPreferredSize(fieldDim);
        sndbutton.setPreferredSize(new Dimension(fieldWidth, 40));

        // Revalidate and repaint so layout updates immediately
        revalidate();
        repaint();
    }

    private void sndbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        String email = txtboxemail.getText();
        char[] password = txtboxemail1.getPassword();
        System.out.println("Attempt login: " + email + " / " + (password.length > 0 ? "[hidden]" : "[empty]"));
        java.util.Arrays.fill(password, '\0');
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }
}
