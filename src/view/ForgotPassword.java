package view;

/**
 *
 * @author Omne
 */
public class ForgotPassword extends javax.swing.JFrame {

        private static final java.util.logging.Logger logger = java.util.logging.Logger
                        .getLogger(ForgotPassword.class.getName());

        public ForgotPassword() {
                initComponents();
                sndbutton.putClientProperty("JButton.buttonType", "roundRect");
sndbutton.putClientProperty("JComponent.arc", 20); // corner radius

backtologin.putClientProperty("JButton.buttonType", "roundRect");
backtologin.putClientProperty("JComponent.arc", 20);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Forgot = new javax.swing.JLabel();
        EmailTxT = new javax.swing.JLabel();
        txtboxemail = new javax.swing.JTextField();
        backtologin = new javax.swing.JButton();
        sndbutton = new javax.swing.JButton();
        imagelbl = new javax.swing.JLabel();
        txtboxemail1 = new javax.swing.JTextField();
        EmailTxT1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 41, 59));
        jPanel1.setForeground(new java.awt.Color(30, 41, 59));

        Forgot.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        Forgot.setForeground(new java.awt.Color(248, 250, 252));
        Forgot.setText("Forgot Password");

        EmailTxT.setForeground(new java.awt.Color(248, 250, 252));
        EmailTxT.setText("Enter your email address to recieve a code ");

        txtboxemail.setForeground(new java.awt.Color(204, 204, 204));
        txtboxemail.setText("Enter the OTP");
        txtboxemail.addActionListener(this::txtboxemailActionPerformed);

        backtologin.setForeground(new java.awt.Color(29, 78, 216));
        backtologin.setText("Back to Login");
        backtologin.setBorderPainted(false);
        backtologin.addActionListener(this::backtologinActionPerformed);

        sndbutton.setBackground(new java.awt.Color(29, 78, 216));
        sndbutton.setForeground(new java.awt.Color(255, 255, 255));
        sndbutton.setText("Send Reset OTP");
        sndbutton.setBorderPainted(false);
        sndbutton.addActionListener(this::sndbuttonActionPerformed);

        imagelbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mylogo.png"))); // NOI18N

        txtboxemail1.setForeground(new java.awt.Color(204, 204, 204));
        txtboxemail1.setText("Enter your email address");
        txtboxemail1.addActionListener(this::txtboxemail1ActionPerformed);

        EmailTxT1.setForeground(new java.awt.Color(248, 250, 252));
        EmailTxT1.setText("-----------------------------------------or----------------------------------------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(imagelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sndbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtboxemail)
                    .addComponent(backtologin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmailTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtboxemail1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addComponent(Forgot)
                    .addComponent(EmailTxT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 356, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(Forgot, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EmailTxT)
                        .addGap(34, 34, 34)
                        .addComponent(txtboxemail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtboxemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EmailTxT1)
                        .addGap(8, 8, 8)
                        .addComponent(backtologin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(imagelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(166, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sndbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sndbuttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sndbuttonActionPerformed

    private void backtologinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtologinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backtologinActionPerformed

    private void txtboxemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtboxemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtboxemailActionPerformed

    private void txtboxemail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtboxemail1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtboxemail1ActionPerformed

       

        public static void main(String args[]) {
                try {
                       
                        javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        logger.log(java.util.logging.Level.SEVERE, null, ex);
                }

                java.awt.EventQueue.invokeLater(() -> new ForgotPassword().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailTxT;
    private javax.swing.JLabel EmailTxT1;
    private javax.swing.JLabel Forgot;
    private javax.swing.JButton backtologin;
    private javax.swing.JLabel imagelbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton sndbutton;
    private javax.swing.JTextField txtboxemail;
    private javax.swing.JTextField txtboxemail1;
    // End of variables declaration//GEN-END:variables
}