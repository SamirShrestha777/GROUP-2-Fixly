package view;

/**
 *
 * @author Omne
 */
public class HomePage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(HomePage.class.getName());

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        javax.swing.ButtonGroup roleGroup = new javax.swing.ButtonGroup();
    roleGroup.add(userbtn);
    roleGroup.add(adminbtn);
    roleGroup.add(techbtn);
    userbtn.setSelected(true); // default selection

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpanel = new javax.swing.JPanel();
        enter = new javax.swing.JLabel();
        Forgot = new javax.swing.JLabel();
        EmailTxT = new javax.swing.JLabel();
        txtboxemail = new javax.swing.JTextField();
        sndbutton = new javax.swing.JButton();
        bglabel = new javax.swing.JLabel();
        frgtpswbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        signupbtn = new javax.swing.JButton();
        passfield = new javax.swing.JPasswordField();
        pwcheckbox = new javax.swing.JCheckBox();
        userbtn = new javax.swing.JRadioButton();
        adminbtn = new javax.swing.JRadioButton();
        techbtn = new javax.swing.JRadioButton();
        techlbl = new javax.swing.JLabel();
        technsgnbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bgpanel.setBackground(new java.awt.Color(30, 41, 59));
        bgpanel.setForeground(new java.awt.Color(30, 41, 59));

        enter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fix (1).png"))); // NOI18N

        Forgot.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Forgot.setForeground(new java.awt.Color(29, 78, 216));
        Forgot.setText("Welcome Back");

        EmailTxT.setBackground(new java.awt.Color(204, 204, 204));
        EmailTxT.setForeground(new java.awt.Color(248, 250, 252));
        EmailTxT.setText("Login into your account");

        txtboxemail.setText("Enter your email address");
        txtboxemail.addActionListener(this::txtboxemailActionPerformed);

        sndbutton.putClientProperty("JButton.buttonType", "roundRect");
        sndbutton.putClientProperty("JComponent.arc", 25);
        sndbutton.setBackground(new java.awt.Color(29, 78, 216));
        sndbutton.setForeground(new java.awt.Color(255, 255, 255));
        sndbutton.setText("Login");
        sndbutton.setBorderPainted(false);
        sndbutton.addActionListener(this::sndbuttonActionPerformed);

        bglabel.setForeground(new java.awt.Color(255, 255, 255));
        bglabel.setText("or");

        frgtpswbtn.setBackground(new java.awt.Color(30, 41, 59));
        frgtpswbtn.setForeground(new java.awt.Color(102, 204, 255));
        frgtpswbtn.setText("Forgot Password");
        frgtpswbtn.setOpaque(true);
        frgtpswbtn.addActionListener(this::frgtpswbtnActionPerformed);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Don't have an account");

        signupbtn.setForeground(new java.awt.Color(102, 102, 255));
        signupbtn.setText("Sign up");

        passfield.setText("Enter your password");

        pwcheckbox.setBackground(new java.awt.Color(30, 41, 59));
        pwcheckbox.setForeground(new java.awt.Color(204, 204, 204));
        pwcheckbox.setText("Show Password");
        pwcheckbox.addActionListener(this::pwcheckboxActionPerformed);

        userbtn.setBackground(new java.awt.Color(30, 41, 59));
        userbtn.setForeground(new java.awt.Color(255, 255, 255));
        userbtn.setText("User");
        userbtn.setOpaque(true);

        adminbtn.setBackground(new java.awt.Color(30, 41, 59));
        adminbtn.setForeground(new java.awt.Color(255, 255, 255));
        adminbtn.setText("Admin");
        adminbtn.setOpaque(true);

        techbtn.setBackground(new java.awt.Color(30, 41, 59));
        techbtn.setForeground(new java.awt.Color(255, 255, 255));
        techbtn.setText("Technician");
        techbtn.setOpaque(true);

        techlbl.setForeground(new java.awt.Color(255, 255, 255));
        techlbl.setText("Are you a technician?");

        technsgnbtn.setForeground(new java.awt.Color(102, 102, 255));
        technsgnbtn.setText("Sign up");

        javax.swing.GroupLayout bgpanelLayout = new javax.swing.GroupLayout(bgpanel);
        bgpanel.setLayout(bgpanelLayout);
        bgpanelLayout.setHorizontalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgpanelLayout.createSequentialGroup()
                .addGap(557, 557, 557)
                .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(539, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgpanelLayout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgpanelLayout.createSequentialGroup()
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgpanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(EmailTxT))
                            .addComponent(Forgot))
                        .addGap(593, 593, 593))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgpanelLayout.createSequentialGroup()
                        .addComponent(frgtpswbtn)
                        .addGap(495, 495, 495))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgpanelLayout.createSequentialGroup()
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(bglabel)
                            .addComponent(techlbl, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(signupbtn)
                            .addComponent(technsgnbtn))
                        .addGap(573, 573, 573))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgpanelLayout.createSequentialGroup()
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgpanelLayout.createSequentialGroup()
                                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtboxemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                                    .addComponent(sndbutton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                                    .addComponent(passfield, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pwcheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgpanelLayout.createSequentialGroup()
                                .addGap(342, 342, 342)
                                .addComponent(userbtn)
                                .addGap(18, 18, 18)
                                .addComponent(adminbtn)
                                .addGap(18, 18, 18)
                                .addComponent(techbtn)))
                        .addGap(210, 210, 210))))
        );
        bgpanelLayout.setVerticalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgpanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userbtn)
                    .addComponent(adminbtn)
                    .addComponent(techbtn))
                .addGap(135, 135, 135)
                .addComponent(enter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Forgot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailTxT)
                .addGap(24, 24, 24)
                .addComponent(txtboxemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwcheckbox))
                .addGap(14, 14, 14)
                .addComponent(frgtpswbtn)
                .addGap(18, 18, 18)
                .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bglabel)
                .addGap(18, 18, 18)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(signupbtn))
                .addGap(18, 18, 18)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(technsgnbtn)
                    .addComponent(techlbl))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void frgtpswbtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_frgtpswbtnActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_frgtpswbtnActionPerformed

    private void pwcheckboxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pwcheckboxActionPerformed
       if (pwcheckbox.isSelected()) {
        passfield.setEchoChar((char) 0);
    } else {
        passfield.setEchoChar('●');
    }
    }// GEN-LAST:event_pwcheckboxActionPerformed

    private void txtboxemailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtboxemailActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtboxemailActionPerformed

    private void sndbuttonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_sndbuttonActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_sndbuttonActionPerformed

    private void txtboxemail1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtboxemail1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtboxemail1ActionPerformed

    /**
     * @param args the command line arguments
     */
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

        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailTxT;
    private javax.swing.JLabel Forgot;
    private javax.swing.JRadioButton adminbtn;
    private javax.swing.JLabel bglabel;
    private javax.swing.JPanel bgpanel;
    private javax.swing.JLabel enter;
    private javax.swing.JButton frgtpswbtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passfield;
    private javax.swing.JCheckBox pwcheckbox;
    private javax.swing.JButton signupbtn;
    private javax.swing.JButton sndbutton;
    private javax.swing.JRadioButton techbtn;
    private javax.swing.JLabel techlbl;
    private javax.swing.JButton technsgnbtn;
    private javax.swing.JTextField txtboxemail;
    private javax.swing.JRadioButton userbtn;
    // End of variables declaration//GEN-END:variables
    public void addLoginListener(java.awt.event.ActionListener listener) {
        sndbutton.addActionListener(listener);
    }

    public void addSignupListener(java.awt.event.ActionListener listener) {
        signupbtn.addActionListener(listener);
    }

    public void addForgotPasswordListener(java.awt.event.ActionListener listener) {
        frgtpswbtn.addActionListener(listener);
    }

    public javax.swing.JTextField getEmailField() {
        return txtboxemail;
    }
public void addTechSignupListener(java.awt.event.ActionListener l) {
    technsgnbtn.addActionListener(l);
}
public javax.swing.JPasswordField getPasswordField() {
    return passfield;
}
    public void removeAllListeners() {
    for (java.awt.event.ActionListener al : sndbutton.getActionListeners()) {
        sndbutton.removeActionListener(al);
    }
    for (java.awt.event.ActionListener al : signupbtn.getActionListeners()) {
        signupbtn.removeActionListener(al);
    }
    for (java.awt.event.ActionListener al : frgtpswbtn.getActionListeners()) {
        frgtpswbtn.removeActionListener(al);
    }
}
    public javax.swing.JRadioButton getUserbtn() {
    return userbtn;
}

public javax.swing.JRadioButton getAdminbtn() {
    return adminbtn;
}

public javax.swing.JRadioButton getTechbtn() {
    return techbtn;
}

public String getSelectedRole() {
    if (adminbtn.isSelected()) return "admin";
    if (techbtn.isSelected()) return "technician";
    return "client";
}
}