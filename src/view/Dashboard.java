package view;

public class Dashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(Dashboard.class.getName());

    public Dashboard() {
        initComponents();

    }

    public void setUsername(String username) {
        wlclabel.setText("Welcome, " + username);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        wlclabel = new javax.swing.JLabel();
        services = new javax.swing.JLabel();
        border = new javax.swing.JPanel();
        sidepanel = new javax.swing.JPanel();
        historybtn = new javax.swing.JButton();
        bookingbtn = new javax.swing.JButton();
        notificationbtn = new javax.swing.JButton();
        technicianbtn = new javax.swing.JButton();
        bgpanel = new javax.swing.JPanel();
        whitepanel = new javax.swing.JPanel();
        lgnbtn = new javax.swing.JButton();
        Createacc1 = new javax.swing.JLabel();
        elecbtn = new javax.swing.JButton();
        cleaningbtn = new javax.swing.JButton();
        painterbtn = new javax.swing.JButton();
        acrepairbtn = new javax.swing.JButton();
        plumbingbtn = new javax.swing.JButton();
        caprenterbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(30, 41, 59));

        wlclabel.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        wlclabel.setForeground(new java.awt.Color(255, 255, 255));
        wlclabel.setText("Welcome,");

        services.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        services.setForeground(new java.awt.Color(242, 242, 242));
        services.setText("Our Services");

        border.setBackground(new java.awt.Color(51, 65, 85));

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        sidepanel.setBackground(new java.awt.Color(30, 41, 59));
        sidepanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(170, 182, 193)));

        historybtn.setBackground(new java.awt.Color(30, 41, 59));
        historybtn.setForeground(new java.awt.Color(255, 255, 255));
        historybtn.setText("🕓   History");
        historybtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));
        historybtn.addActionListener(this::historybtnActionPerformed);

        bookingbtn.setBackground(new java.awt.Color(30, 41, 59));
        bookingbtn.setForeground(new java.awt.Color(255, 255, 255));
        bookingbtn.setText("📅   Booking");
        bookingbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));
        bookingbtn.addActionListener(this::bookingbtnActionPerformed);

        notificationbtn.setBackground(new java.awt.Color(30, 41, 59));
        notificationbtn.setForeground(new java.awt.Color(255, 255, 255));
        notificationbtn.setText("🔔   Notification");
        notificationbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        technicianbtn.setBackground(new java.awt.Color(30, 41, 59));
        technicianbtn.setForeground(new java.awt.Color(255, 255, 255));
        technicianbtn.setText("👨‍   Profile");
        technicianbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 65, 85)));

        javax.swing.GroupLayout sidepanelLayout = new javax.swing.GroupLayout(sidepanel);
        sidepanel.setLayout(sidepanelLayout);
        sidepanelLayout.setHorizontalGroup(
            sidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidepanelLayout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(historybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bookingbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(notificationbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(technicianbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        sidepanelLayout.setVerticalGroup(
            sidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(historybtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bookingbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(notificationbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(technicianbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        );

        bgpanel.setBackground(new java.awt.Color(51, 65, 85));

        javax.swing.GroupLayout bgpanelLayout = new javax.swing.GroupLayout(bgpanel);
        bgpanel.setLayout(bgpanelLayout);
        bgpanelLayout.setHorizontalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bgpanelLayout.setVerticalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        whitepanel.setBackground(new java.awt.Color(255, 255, 255));
        whitepanel.setForeground(new java.awt.Color(204, 204, 204));

        lgnbtn.setForeground(new java.awt.Color(29, 78, 216));
        lgnbtn.setText("➜🚪  Logout");
        lgnbtn.addActionListener(this::lgnbtnActionPerformed);

        Createacc1.setBackground(new java.awt.Color(255, 255, 255));
        Createacc1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        Createacc1.setForeground(new java.awt.Color(29, 78, 216));
        Createacc1.setText("Fixly");

        javax.swing.GroupLayout whitepanelLayout = new javax.swing.GroupLayout(whitepanel);
        whitepanel.setLayout(whitepanelLayout);
        whitepanelLayout.setHorizontalGroup(
            whitepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, whitepanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(Createacc1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lgnbtn)
                .addGap(47, 47, 47))
        );
        whitepanelLayout.setVerticalGroup(
            whitepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(whitepanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(whitepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lgnbtn)
                    .addComponent(Createacc1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        elecbtn.setBackground(new java.awt.Color(0, 102, 204));
        elecbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        elecbtn.setForeground(new java.awt.Color(255, 255, 255));
        elecbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/elec.png"))); // NOI18N

        cleaningbtn.setBackground(new java.awt.Color(0, 102, 204));
        cleaningbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cleaningbtn.setForeground(new java.awt.Color(255, 255, 255));
        cleaningbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cln.png"))); // NOI18N

        painterbtn.setBackground(new java.awt.Color(0, 102, 204));
        painterbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        painterbtn.setForeground(new java.awt.Color(255, 255, 255));
        painterbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pnt.png"))); // NOI18N

        acrepairbtn.setBackground(new java.awt.Color(0, 102, 204));
        acrepairbtn.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        acrepairbtn.setForeground(new java.awt.Color(255, 255, 255));
        acrepairbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ac.png"))); // NOI18N
        acrepairbtn.setToolTipText("");
        acrepairbtn.addActionListener(this::acrepairbtnActionPerformed);

        plumbingbtn.setBackground(new java.awt.Color(0, 102, 204));
        plumbingbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        plumbingbtn.setForeground(new java.awt.Color(255, 255, 255));
        plumbingbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plumberlg.png"))); // NOI18N
        plumbingbtn.addActionListener(this::plumbingbtnActionPerformed);

        caprenterbtn.setBackground(new java.awt.Color(0, 102, 204));
        caprenterbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        caprenterbtn.setForeground(new java.awt.Color(255, 255, 255));
        caprenterbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carp.png"))); // NOI18N
        caprenterbtn.addActionListener(this::caprenterbtnActionPerformed);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(whitepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sidepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(plumbingbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                            .addComponent(caprenterbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painterbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(elecbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                            .addComponent(cleaningbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(acrepairbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bgpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(services)
                            .addComponent(wlclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(whitepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(border, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(wlclabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(services)
                        .addGap(38, 38, 38)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(elecbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(plumbingbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cleaningbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(caprenterbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(painterbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(acrepairbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bgpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void historybtnActionPerformed(java.awt.event.ActionEvent evt) {}
    private void bookingbtnActionPerformed(java.awt.event.ActionEvent evt) {}
    private void lgnbtnActionPerformed(java.awt.event.ActionEvent evt) {}
    private void plumbingbtnActionPerformed(java.awt.event.ActionEvent evt) {}
    private void acrepairbtnActionPerformed(java.awt.event.ActionEvent evt) {}
    private void caprenterbtnActionPerformed(java.awt.event.ActionEvent evt) {}

    // listener registration methods for DashboardController
    public void addBookingListener(java.awt.event.ActionListener l)      { bookingbtn.addActionListener(l); }
    public void addHistoryListener(java.awt.event.ActionListener l)      { historybtn.addActionListener(l); }
    public void addNotificationListener(java.awt.event.ActionListener l) { notificationbtn.addActionListener(l); }
    public void addProfileListener(java.awt.event.ActionListener l)      { technicianbtn.addActionListener(l); }
    public void addPlumbingListener(java.awt.event.ActionListener l)     { plumbingbtn.addActionListener(l); }
    public void addElectricalListener(java.awt.event.ActionListener l)   { elecbtn.addActionListener(l); }
    public void addCleaningListener(java.awt.event.ActionListener l)     { cleaningbtn.addActionListener(l); }
    public void addCarpentryListener(java.awt.event.ActionListener l)    { caprenterbtn.addActionListener(l); }
    public void addPaintingListener(java.awt.event.ActionListener l)     { painterbtn.addActionListener(l); }
    public void addACRepairListener(java.awt.event.ActionListener l)     { acrepairbtn.addActionListener(l); }
    public void addLogoutListener(java.awt.event.ActionListener l)       { lgnbtn.addActionListener(l); }


    public static void main(String args[]) {
        try {
            javax.swing.UIManager.put("TextComponent.arc", 10);
            javax.swing.UIManager.put("Button.arc", 15);
            javax.swing.UIManager.put("Component.arc", 10);
            javax.swing.UIManager.put("ProgressBar.arc", 10);
            javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Createacc1;
    private javax.swing.JButton acrepairbtn;
    private javax.swing.JPanel background;
    private javax.swing.JPanel bgpanel;
    private javax.swing.JButton bookingbtn;
    private javax.swing.JPanel border;
    private javax.swing.JButton caprenterbtn;
    private javax.swing.JButton cleaningbtn;
    private javax.swing.JButton elecbtn;
    private javax.swing.JButton historybtn;
    private javax.swing.JButton lgnbtn;
    private javax.swing.JButton notificationbtn;
    private javax.swing.JButton painterbtn;
    private javax.swing.JButton plumbingbtn;
    private javax.swing.JLabel services;
    private javax.swing.JPanel sidepanel;
    private javax.swing.JButton technicianbtn;
    private javax.swing.JPanel whitepanel;
    private javax.swing.JLabel wlclabel;
    // End of variables declaration//GEN-END:variables
}