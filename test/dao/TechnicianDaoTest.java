package dao;

import model.UserData;
import model.Appointment;
import org.junit.Test;
import static org.junit.Assert.*;

public class TechnicianDaoTest {

    private UserData buildTechnician() {
        UserData tech = new UserData();
        long ts = System.currentTimeMillis();
        tech.setUsername("techDaoTest" + ts);
        tech.setEmail("techdaotest" + ts + "@fixly.com");
        tech.setPassword("pass123");
        tech.setSpecialization("Carpenter");
        return tech;
    }

    @Test
    public void testCreateTechnicianWithCert() {
        System.out.println("createTechnicianWithCert");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        boolean exists = instance.checkTechnician(tech.getEmail());
        assertTrue("Technician should exist after creation", exists);
    }

    @Test
    public void testIsVerified_FalseByDefault() {
        System.out.println("isVerified - false by default");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        boolean result = instance.isVerified(tech.getEmail());
        assertFalse("New technician should not be verified by default", result);
    }

    @Test
    public void testLoginTechnician_FailsWhenUnverified() {
        System.out.println("loginTechnician - fails when unverified");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        boolean result = instance.loginTechnician(tech.getEmail(), tech.getPassword());
        assertFalse("Login should fail for unverified technician", result);
    }

    @Test
    public void testApproveTechnician_AllowsLogin() {
        System.out.println("approveTechnician - allows login after approval");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        int techId = instance.getIdByEmail(tech.getEmail());
        boolean approveResult = instance.approveTechnician(techId);
        assertTrue("Approval should succeed", approveResult);

        boolean loginResult = instance.loginTechnician(tech.getEmail(), tech.getPassword());
        assertTrue("Login should succeed after approval", loginResult);
    }

    @Test
    public void testRejectTechnician() {
        System.out.println("rejectTechnician");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        int techId = instance.getIdByEmail(tech.getEmail());
        boolean result = instance.rejectTechnician(techId);
        assertTrue("Rejection should succeed", result);
    }

    @Test
    public void testGetIdByEmail() {
        System.out.println("getIdByEmail");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        int result = instance.getIdByEmail(tech.getEmail());
        assertTrue("Id should be positive for existing technician", result > 0);
    }

    @Test
    public void testGetUsernameByEmail() {
        System.out.println("getUsernameByEmail");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        String result = instance.getUsernameByEmail(tech.getEmail());
        assertEquals(tech.getUsername(), result);
    }

    @Test
    public void testGetSpecializationByEmail() {
        System.out.println("getSpecializationByEmail");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        String result = instance.getSpecializationByEmail(tech.getEmail());
        assertEquals("Carpenter", result);
    }

    @Test
    public void testGetTechnicianById() {
        System.out.println("getTechnicianById");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        int techId = instance.getIdByEmail(tech.getEmail());
        UserData result = instance.getTechnicianById(techId);

        assertNotNull(result);
        assertEquals(tech.getEmail(), result.getEmail());
    }

    @Test
    public void testGetPendingTechnicians() {
        System.out.println("getPendingTechnicians");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");

        java.util.List<UserData> result = instance.getPendingTechnicians();
        assertFalse("Should contain at least the newly created pending technician", result.isEmpty());
    }

    @Test
    public void testGetApprovedTechnicians() {
        System.out.println("getApprovedTechnicians");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");
        int techId = instance.getIdByEmail(tech.getEmail());
        instance.approveTechnician(techId);

        java.util.List<UserData> result = instance.getApprovedTechnicians();
        assertFalse(result.isEmpty());
    }

    @Test
    public void testUpdateAccountStatus() {
        System.out.println("updateAccountStatus");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");
        int techId = instance.getIdByEmail(tech.getEmail());

        boolean result = instance.updateAccountStatus(techId, "suspended");
        assertTrue("Account status update should succeed", result);
    }

    @Test
    public void testGetAllVerifiedTechnicians() {
        System.out.println("getAllVerifiedTechnicians");
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");
        int techId = instance.getIdByEmail(tech.getEmail());
        instance.approveTechnician(techId);

        java.util.List<UserData> result = instance.getAllVerifiedTechnicians();
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAcceptJob() {
        System.out.println("acceptJob");
        // Set up a client + appointment
        UserData client = new UserData("jobClient" + System.currentTimeMillis(),
                "jobclient" + System.currentTimeMillis() + "@fixly.com", "pass123", "addr");
        UserDao userDao = new UserDao();
        userDao.createUser(client);
        int clientId = userDao.getIdByEmail(client.getEmail());

        Appointment a = new Appointment();
        a.setClientId(clientId);
        a.setServiceType("Carpenter");
        a.setDate("2026-07-01");
        a.setTime("11:00 AM");
        a.setNotes("test");
        a.setAddress("test addr");
        a.setStatus("pending");
        a.setPaymentMethod("cash");
        AppointmentDao apptDao = new AppointmentDao();
        apptDao.saveAppointment(a);
        int appointmentId = apptDao.getAppointmentsByUser(clientId).get(0).getId();

        // Set up technician
        UserData tech = buildTechnician();
        TechnicianDao instance = new TechnicianDao();
        instance.createTechnicianWithCert(tech, "/certs/test.pdf");
        int techId = instance.getIdByEmail(tech.getEmail());

        boolean result = instance.acceptJob(appointmentId, techId);
        assertTrue("Accept job should succeed", result);
    }
}