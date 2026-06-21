/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.UserData;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Omne
 */
public class UserDaoTest {

    public UserDaoTest() {
    }

    /**
     * Test of createUser method, of class UserDao.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        String uniqueEmail = "test" + System.currentTimeMillis() + "@fixly.com";
        UserData user = new UserData("ram", uniqueEmail, "asdsads", "rameshnagar");
        UserDao instance = new UserDao();
        instance.createUser(user);

        boolean exists = instance.checkUser(user);
        assertTrue("User should exist in DB after creation", exists);
    }

    /**
     * Test of checkUser method, of class UserDao.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        UserData user = new UserData();
        user.setEmail("nonexistent" + System.currentTimeMillis() + "@fixly.com");
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.checkUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class UserDao.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String uniqueEmail = "logintest" + System.currentTimeMillis() + "@fixly.com";
        String password = "mypassword123";

        UserData newUser = new UserData("loginuser", uniqueEmail, password, "testaddress");
        UserDao instance = new UserDao();
        instance.createUser(newUser);

        UserData loginAttempt = new UserData();
        loginAttempt.setEmail(uniqueEmail);
        loginAttempt.setPassword(password);

        boolean result = instance.loginUser(loginAttempt);
        assertTrue("Login should succeed with correct credentials", result);
    }

    /**
     * Test of resetPassword method, of class UserDao.
     */
    @Test
    public void testResetPassword() {
        System.out.println("resetPassword");
        String uniqueEmail = "resettest" + System.currentTimeMillis() + "@fixly.com";

        UserData newUser = new UserData("resetuser", uniqueEmail, "oldpassword", "testaddress");
        UserDao instance = new UserDao();
        instance.createUser(newUser);

        String newPassword = "newpassword456";
        boolean result = instance.resetPassword(uniqueEmail, newPassword);
        assertTrue("Password reset should succeed for existing user", result);
    }

    /**
     * Test of updateProfile method, of class UserDao.
     */
    @Test
    public void testUpdateProfile() {
        System.out.println("updateProfile");
        String uniqueEmail = "updatetest" + System.currentTimeMillis() + "@fixly.com";

        UserData newUser = new UserData("originalname", uniqueEmail, "password123", "testaddress");
        UserDao instance = new UserDao();
        instance.createUser(newUser);

        int userId = instance.getIdByEmail(uniqueEmail);
        assertTrue("User should have a valid id after creation", userId > 0);

        String updatedUsername = "updatedname";
        String updatedEmail = "updated" + System.currentTimeMillis() + "@fixly.com";
        String updatedPassword = "newpassword789";

        boolean result = instance.updateProfile(userId, updatedUsername, updatedEmail, updatedPassword);
        assertTrue("Profile update should succeed", result);
    }

    /**
     * Test of getUserById method, of class UserDao.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        String uniqueEmail = "getbyid" + System.currentTimeMillis() + "@fixly.com";

        UserData newUser = new UserData("findme", uniqueEmail, "password123", "testaddress");
        UserDao instance = new UserDao();
        instance.createUser(newUser);

        int userId = instance.getIdByEmail(uniqueEmail);
        UserData result = instance.getUserById(userId);

        assertNotNull("User should be found by id", result);
        assertEquals(uniqueEmail, result.getEmail());
    }

    /**
     * Test of getUsernameByEmail method, of class UserDao.
     */
    @Test
    public void testGetUsernameByEmail() {
        System.out.println("getUsernameByEmail");
        String uniqueEmail = "usernametest" + System.currentTimeMillis() + "@fixly.com";
        String expectedUsername = "myusername";

        UserData newUser = new UserData(expectedUsername, uniqueEmail, "password123", "testaddress");
        UserDao instance = new UserDao();
        instance.createUser(newUser);

        String result = instance.getUsernameByEmail(uniqueEmail);
        assertEquals(expectedUsername, result);
    }

    /**
     * Test of getIdByEmail method, of class UserDao.
     */
    @Test
    public void testGetIdByEmail() {
        System.out.println("getIdByEmail");
        String uniqueEmail = "idtest" + System.currentTimeMillis() + "@fixly.com";

        UserData newUser = new UserData("iduser", uniqueEmail, "password123", "testaddress");
        UserDao instance = new UserDao();
        instance.createUser(newUser);

        int result = instance.getIdByEmail(uniqueEmail);
        assertTrue("Id should be a positive number for existing user", result > 0);
    }

    /**
     * Test of getIdByEmail method for a non-existent user.
     */
    @Test
    public void testGetIdByEmail_NotFound() {
        System.out.println("getIdByEmail - not found");
        UserDao instance = new UserDao();
        String email = "doesnotexist" + System.currentTimeMillis() + "@fixly.com";
        int expResult = -1;
        int result = instance.getIdByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of initTable method, of class UserDao.
     */
    @Test
    public void testInitTable() {
        System.out.println("initTable");
        UserDao instance = new UserDao();
        instance.initTable();
        // No exception thrown means table creation/verification succeeded
    }

}