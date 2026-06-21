package dao;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdminDaoTest {

    @Test
    public void testLoginAdmin_Success() {
        System.out.println("loginAdmin - success");
        AdminDao instance = new AdminDao();
        boolean result = instance.loginAdmin("fixlynepal@gmail.com", "fixly@69");
        assertTrue("Login should succeed with correct hardcoded credentials", result);
    }

    @Test
    public void testLoginAdmin_WrongPassword() {
        System.out.println("loginAdmin - wrong password");
        AdminDao instance = new AdminDao();
        boolean result = instance.loginAdmin("fixlynepal@gmail.com", "wrongpassword");
        assertFalse("Login should fail with incorrect password", result);
    }

    @Test
    public void testLoginAdmin_WrongEmail() {
        System.out.println("loginAdmin - wrong email");
        AdminDao instance = new AdminDao();
        boolean result = instance.loginAdmin("notadmin@fixly.com", "fixly@69");
        assertFalse("Login should fail with incorrect email", result);
    }

    @Test
    public void testLogAction() {
        System.out.println("logAction");
        AdminDao instance = new AdminDao();
        instance.logAction("Test action " + System.currentTimeMillis());
        // No exception thrown means insert succeeded
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        AdminDao instance = new AdminDao();
        java.util.List<String[]> result = instance.getAllUsers();
        assertNotNull(result);
    }

    @Test
    public void testGetAllTechnicians() {
        System.out.println("getAllTechnicians");
        AdminDao instance = new AdminDao();
        java.util.List<String[]> result = instance.getAllTechnicians();
        assertNotNull(result);
    }

    @Test
    public void testGetAllBookings() {
        System.out.println("getAllBookings");
        AdminDao instance = new AdminDao();
        java.util.List<String[]> result = instance.getAllBookings();
        assertNotNull(result);
    }

    @Test
    public void testInitTable() {
        System.out.println("initTable");
        AdminDao instance = new AdminDao();
        instance.initTable();
        // No exception thrown means table creation/verification succeeded
    }
}