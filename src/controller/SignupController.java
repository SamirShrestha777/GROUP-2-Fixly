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
    private final HomePage homeView;

    public SignupController(SignupPage userView, HomePage homeView) {
        this.userView = userView;
        this.homeView = homeView;
        userView.addSignupListener(new SignupListener());
        userView.addLoginListener(new LoginListener());

        setPasswordPlaceholder(userView.getPasswordField(), "Enter your password");
        setPasswordPlaceholder(userView.getCPasswordField(), "Confirm your password");

        userView.getShowPasswordCheckbox().addActionListener(e -> {
            boolean show = userView.getShowPasswordCheckbox().isSelected();
            String psw  = new String(userView.getPasswordField().getPassword());
            String cpsw = new String(userView.getCPasswordField().getPassword());
            if (!psw.equals("Enter your password")) {
                userView.getPasswordField().setEchoChar(show ? (char) 0 : '●');
            }
            if (!cpsw.equals("Confirm your password")) {
                userView.getCPasswordField().setEchoChar(show ? (char) 0 : '●');
            }
        });
    }

    public void open()  { this.userView.setVisible(true); }
    public void close() { this.userView.dispose(); }

    private void setPasswordPlaceholder(javax.swing.JPasswordField field, String placeholder) {
        field.setEchoChar((char) 0);
        field.setText(placeholder);
        field.setForeground(java.awt.Color.GRAY);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(java.awt.Color.BLACK);
                    field.setEchoChar('●');
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setEchoChar((char) 0);
                    field.setText(placeholder);
                    field.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name      = userView.getNameField().getText().trim();
                String email     = userView.getEmailField().getText().trim();
                String password  = new String(userView.getPasswordField().getPassword()).trim();
                String cpassword = new String(userView.getCPasswordField().getPassword()).trim();
                String address   = userView.getAddressField().getText().trim();

                if (name.isEmpty()     || name.equals("Enter your full name") ||
                    email.isEmpty()    || email.equals("Enter your email address") ||
                    password.isEmpty() || password.equals("Enter your password") ||
                    address.isEmpty()  || address.equals("Enter your address")) {
                    JOptionPane.showMessageDialog(userView, "Please fill in all fields.");
                    return;
                }

                if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
                    JOptionPane.showMessageDialog(userView,
                        "Please enter a valid email address (e.g. example@gmail.com).");
                    return;
                }

                if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                    JOptionPane.showMessageDialog(userView,
                        "Password must be at least 8 characters,\n" +
                        "include one uppercase letter and one number.");
                    return;
                }

                if (!password.equals(cpassword)) {
                    JOptionPane.showMessageDialog(userView, "Passwords do not match.");
                    return;
                }

                UserData user = new UserData();
                user.setUsername(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setAddress(address);
                user.setRole("client");

                boolean exists = userDao.checkUser(user);
                if (exists) {
                    JOptionPane.showMessageDialog(userView, "Email already registered.");
                } else {
                    userDao.createUser(user);
                    JOptionPane.showMessageDialog(userView, "Account created successfully!");
                    close();
                    homeView.setVisible(true);
                }
            } catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
                JOptionPane.showMessageDialog(userView, "Error: " + ex.getMessage());
            }
        }
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            homeView.setVisible(true);
        }
    }
}