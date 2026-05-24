package controller;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.UserData;
import view.HomePage;
import view.SignupPage;

public class SignupController {

    private final UserDao userDao = new UserDao();
    private final SignupPage userView;

    public SignupController(SignupPage userView) {
        this.userView = userView;
        userView.addSignupListener(new SignupListener());
        userView.addLoginListener(new LoginListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name      = userView.getNameField().getText();
                String email     = userView.getEmailField().getText();
                String password  = userView.getPasswordField().getText();
                String cpassword = userView.getCPasswordField().getText();
                String address   = userView.getAddressField().getText();

                // Validate empty fields
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(userView, "Please fill in all fields.");
                    return;
                }

                // Validate passwords match
                if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(userView, "Passwords do not match.");
                    return;
                }

                // Build user object
                UserData user = new UserData();
                user.setUsername(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setAddress(address);

                // Check duplicate then save
                boolean exists = userDao.checkUser(user);
                if (exists) {
                    JOptionPane.showMessageDialog(userView, "Email already registered.");
                } else {
                    userDao.createUser(user);
                    JOptionPane.showMessageDialog(userView, "Account created successfully!");
                }

            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
            }
        }
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            HomePage homeView = new HomePage();
            homeView.setVisible(true);
        }
    }
}