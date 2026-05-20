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
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        enter = new javax.swing.JLabel();
        Forgot = new javax.swing.JLabel();
        EmailTxT = new javax.swing.JLabel();
        txtboxemail = new javax.swing.JTextField();
        backtologin = new javax.swing.JButton();
        or = new javax.swing.JLabel();
        sndbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 41, 59));
        jPanel1.setForeground(new java.awt.Color(30, 41, 59));

        enter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/wow-Photoroom (1).png"))); // NOI18N

        Forgot.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Forgot.setForeground(new java.awt.Color(248, 250, 252));
        Forgot.setText("Forgot Password");

        EmailTxT.setForeground(new java.awt.Color(248, 250, 252));
        EmailTxT.setText("Enter your email address to recieve a code ");

        txtboxemail.setForeground(new java.awt.Color(204, 204, 204));
        txtboxemail.setText("Enter your email address");
        txtboxemail.addActionListener(this::txtboxemailActionPerformed);

        backtologin.setForeground(new java.awt.Color(29, 78, 216));
        backtologin.setText("Back to Login");
        backtologin.setBorderPainted(false);
        backtologin.addActionListener(this::backtologinActionPerformed);

        or.setForeground(new java.awt.Color(204, 204, 204));
        or.setText("or");

        sndbutton.setBackground(new java.awt.Color(29, 78, 216));
        sndbutton.setForeground(new java.awt.Color(255, 255, 255));
        sndbutton.setText("Send Reset OTP");
        sndbutton.setBorderPainted(false);
        sndbutton.addActionListener(this::sndbuttonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backtologin, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmailTxT)
                            .addComponent(Forgot)
                            .addComponent(txtboxemail, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(or)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Forgot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EmailTxT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtboxemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sndbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(or)
                        .addGap(3, 3, 3)
                        .addComponent(backtologin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void txtboxemailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtboxemailActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_txtboxemailActionPerformed

        private void backtologinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backtologinActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_backtologinActionPerformed

        private void sndbuttonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_sndbuttonActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_sndbuttonActionPerformed

        public static void main(String args[]) {
                try {
                        javax.swing.UIManager.put("TextComponent.arc", 20); // ← ADD THIS LINE
                        javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        logger.log(java.util.logging.Level.SEVERE, null, ex);
                }

                java.awt.EventQueue.invokeLater(() -> new ForgotPassword().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailTxT;
    private javax.swing.JLabel Forgot;
    private javax.swing.JButton backtologin;
    private javax.swing.JLabel enter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel or;
    private javax.swing.JButton sndbutton;
    private javax.swing.JTextField txtboxemail;
    // End of variables declaration//GEN-END:variables
}