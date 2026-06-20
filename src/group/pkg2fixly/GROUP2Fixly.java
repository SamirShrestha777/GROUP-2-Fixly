package group.pkg2fixly;

import controller.HomeController;
import dao.UserDao;
import database.MySqlConnector;
import database.db;
import view.HomePage;

public class GROUP2Fixly {

    public static void main(String[] args) {
        try {
            // Apply a modern, moderate curve to text fields (ID and PW)
            javax.swing.UIManager.put("TextComponent.arc", 10);
            
            // Apply a nice curve to buttons without making them full pills
            javax.swing.UIManager.put("Button.arc", 15);
            
            // Keep general components consistent
            javax.swing.UIManager.put("Component.arc", 10);
            javax.swing.UIManager.put("ProgressBar.arc", 10);
            
            javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        db data = new MySqlConnector();
        if (data.openConnection() != null) {
            System.out.println("Connection successful");

            UserDao userDao = new UserDao();
            userDao.initTable();

            HomePage homeView = new HomePage();
            HomeController homeController = new HomeController(homeView);
            homeController.open();

        } else {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Could not connect to database.\nPlease check your connection.",
                "Connection Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}