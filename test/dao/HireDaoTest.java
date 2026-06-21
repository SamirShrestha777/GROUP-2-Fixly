package dao;

import model.UserData;
import org.junit.Test;
import static org.junit.Assert.*;

public class HireDaoTest {

    private int createTestClient() {
        UserData user = new UserData("hireClient" + System.currentTimeMillis(),
                "hireclient" + System.currentTimeMillis() + "@fixly.com", "pass123", "test address");
        UserDao userDao = new UserDao();
        userDao.createUser(user);
        return userDao.getIdByEmail(user.getEmail());
    }

    private int createTestTechnician() {
        UserData tech = new UserData();
        tech.setUsername("hireTech" + System.currentTimeMillis());
        tech.setEmail("hiretech" + System.currentTimeMillis() + "@fixly.com");
        tech.setPassword("pass123");
        tech.setSpecialization("Electrician");
        TechnicianDao techDao = new TechnicianDao();
        techDao.createTechnicianWithCert(tech, "/certs/test.pdf");
        return techDao.getIdByEmail(tech.getEmail());
    }

    @Test
    public void testHireTechnician() {
        System.out.println("hireTechnician");
        int clientId = createTestClient();
        int techId = createTestTechnician();

        HireDao instance = new HireDao();
        boolean result = instance.hireTechnician(clientId, techId);
        assertTrue("Hire should succeed", result);
    }

    @Test
    public void testIsAlreadyHired_True() {
        System.out.println("isAlreadyHired - true");
        int clientId = createTestClient();
        int techId = createTestTechnician();

        HireDao instance = new HireDao();
        instance.hireTechnician(clientId, techId);

        boolean result = instance.isAlreadyHired(clientId, techId);
        assertTrue("Should be true after hiring", result);
    }

    @Test
    public void testIsAlreadyHired_False() {
        System.out.println("isAlreadyHired - false");
        int clientId = createTestClient();
        int techId = createTestTechnician();

        HireDao instance = new HireDao();
        boolean result = instance.isAlreadyHired(clientId, techId);
        assertFalse("Should be false when not hired yet", result);
    }

    @Test
    public void testHireTechnician_DuplicateIgnored() {
        System.out.println("hireTechnician - duplicate ignored");
        int clientId = createTestClient();
        int techId = createTestTechnician();

        HireDao instance = new HireDao();
        instance.hireTechnician(clientId, techId);
        // Second hire attempt — INSERT IGNORE means this returns false (0 rows affected) due to UNIQUE KEY
        boolean result = instance.hireTechnician(clientId, techId);
        assertFalse("Duplicate hire should not insert a new row", result);

        // But the original hire should still be there
        assertTrue(instance.isAlreadyHired(clientId, techId));
    }
}