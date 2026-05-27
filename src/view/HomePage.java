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

        jPanel1 = new javax.swing.JPanel();
        enter = new javax.swing.JLabel();
        Forgot = new javax.swing.JLabel();
        EmailTxT = new javax.swing.JLabel();
        txtboxemail = new javax.swing.JTextField();
        sndbutton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        frgtpswbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        signupbtn = new javax.swing.JButton();
        passfield = new javax.swing.JPasswordField();
        pwcheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 41, 59));
        jPanel1.setForeground(new java.awt.Color(30, 41, 59));

        enter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fix (1).png"))); // NOI18N

        Forgot.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Forgot.setForeground(new java.awt.Color(29, 78, 216));
        Forgot.setText("Welcome Back");

        EmailTxT.setBackground(new java.awt.Color(204, 204, 204));
        EmailTxT.setForeground(new java.awt.Color(248, 250, 252));
        EmailTxT.setText("Login into your account");

        txtboxemail.setForeground(new java.awt.Color(204, 204, 204));
        txtboxemail.setText("Enter your email address");
        txtboxemail.addActionListener(this::txtboxemailActionPerformed);

        sndbutton.putClientProperty("JButton.buttonType", "roundRect");
        sndbutton.putClientProperty("JComponent.arc", 25);
        sndbutton.setBackground(new java.awt.Color(29, 78, 216));
        sndbutton.setForeground(new java.awt.Color(255, 255, 255));
        sndbutton.setText("Login");
        sndbutton.setBorderPainted(false);
        sndbutton.addActionListener(this::sndbuttonActionPerformed);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("or");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(557, 557, 557)
                .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(539, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(525, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(EmailTxT))
                            .addComponent(Forgot))
                        .addGap(593, 593, 593))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(frgtpswbtn)
                        .addGap(495, 495, 495))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signupbtn)
                        .addGap(573, 573, 573))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtboxemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addComponent(sndbutton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addComponent(passfield, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pwcheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(377, 377, 377))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(enter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Forgot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailTxT)
                .addGap(18, 18, 18)
                .addComponent(txtboxemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwcheckbox))
                .addGap(14, 14, 14)
                .addComponent(frgtpswbtn)
                .addGap(18, 18, 18)
                .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(signupbtn))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailTxT;
    private javax.swing.JLabel Forgot;
    private javax.swing.JLabel enter;
    private javax.swing.JButton frgtpswbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passfield;
    private javax.swing.JCheckBox pwcheckbox;
    private javax.swing.JButton signupbtn;
    private javax.swing.JButton sndbutton;
    private javax.swing.JTextField txtboxemail;
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

    public javax.swing.JTextField getPasswordField() {
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
}