package view;

import javax.swing.JFrame;

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        enter = new javax.swing.JLabel();
        Forgot = new javax.swing.JLabel();
        EmailTxT = new javax.swing.JLabel();
        txtboxemail = new javax.swing.JTextField();
        sndbutton = new javax.swing.JButton();
        txtboxemail1 = new javax.swing.JTextField();

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

        sndbutton.setBackground(new java.awt.Color(29, 78, 216));
        sndbutton.setForeground(new java.awt.Color(255, 255, 255));
        sndbutton.setText("Login");
        sndbutton.setBorderPainted(false);
        sndbutton.addActionListener(this::sndbuttonActionPerformed);

        txtboxemail1.setForeground(new java.awt.Color(204, 204, 204));
        txtboxemail1.setText("Enter your password");
        txtboxemail1.addActionListener(this::txtboxemail1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(634, 634, 634)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Forgot)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(EmailTxT)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(586, 586, 586)
                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(557, 557, 557)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtboxemail1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtboxemail, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(476, Short.MAX_VALUE))
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
                .addGap(26, 26, 26)
                .addComponent(txtboxemail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton sndbutton;
    private javax.swing.JTextField txtboxemail;
    private javax.swing.JTextField txtboxemail1;
    // End of variables declaration//GEN-END:variables
}