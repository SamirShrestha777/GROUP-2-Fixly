package group.pkg2fixly;

import controller.HomeController;
import dao.UserDao;
import database.MySqlConnector;
import database.db;
import view.HomePage;

public class GROUP2Fixly {

    public static void main(String[] args) {
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