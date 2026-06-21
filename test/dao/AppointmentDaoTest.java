package dao;

import model.Appointment;
import model.UserData;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppointmentDaoTest {

    private int createTestClient() {
        UserData user = new UserData("apptUser" + System.currentTimeMillis(),
                "appt" + System.currentTimeMillis() + "@fixly.com", "pass123", "test address");
        UserDao userDao = new UserDao();
        userDao.createUser(user);
        return userDao.getIdByEmail(user.getEmail());
    }

    private Appointment buildAppointment(int clientId) {
        Appointment a = new Appointment();
        a.setClientId(clientId);
        a.setServiceType("Plumber");
        a.setDate("2026-07-01");
        a.setTime("10:00 AM");
        a.setNotes("Test notes");
        a.setAddress("Test address");
        a.setStatus("pending");
        a.setPaymentMethod("cash");
        return a;
    }

    @Test
    public void testSaveAppointment() {
        System.out.println("saveAppointment");
        int clientId = createTestClient();
        Appointment appointment = buildAppointment(clientId);
        AppointmentDao instance = new AppointmentDao();
        boolean result = instance.saveAppointment(appointment);
        assertTrue("Appointment should save successfully", result);
    }

    @Test
    public void testGetAppointmentsByUser() {
        System.out.println("getAppointmentsByUser");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        instance.saveAppointment(buildAppointment(clientId));

        java.util.List<Appointment> result = instance.getAppointmentsByUser(clientId);
        assertFalse("Should return at least one appointment", result.isEmpty());
        assertEquals(clientId, result.get(0).getClientId());
    }

    @Test
    public void testGetPendingAppointments() {
        System.out.println("getPendingAppointments");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        instance.saveAppointment(buildAppointment(clientId));

        java.util.List<Appointment> result = instance.getPendingAppointments();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetPendingAppointmentsByService() {
        System.out.println("getPendingAppointmentsByService");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        instance.saveAppointment(buildAppointment(clientId));

        java.util.List<Appointment> result = instance.getPendingAppointmentsByService("Plumber");
        assertFalse(result.isEmpty());
        assertEquals("Plumber", result.get(0).getServiceType());
    }

    @Test
    public void testUpdateAppointmentStatus() {
        System.out.println("updateAppointmentStatus");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        instance.saveAppointment(buildAppointment(clientId));

        java.util.List<Appointment> appts = instance.getAppointmentsByUser(clientId);
        int appointmentId = appts.get(0).getId();

        boolean result = instance.updateAppointmentStatus(appointmentId, "completed");
        assertTrue("Status update should succeed", result);
    }

    @Test
    public void testSubmitPaymentProof() {
        System.out.println("submitPaymentProof");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        Appointment a = buildAppointment(clientId);
        a.setStatus("awaiting_payment");
        instance.saveAppointment(a);

        java.util.List<Appointment> appts = instance.getAppointmentsByUser(clientId);
        int appointmentId = appts.get(0).getId();

        boolean result = instance.submitPaymentProof(appointmentId, "/uploads/proof123.png");
        assertTrue("Payment proof submission should succeed", result);

        String path = instance.getPaymentProofPathById(appointmentId);
        assertEquals("/uploads/proof123.png", path);
    }

    @Test
    public void testGetAppointmentsByStatus() {
        System.out.println("getAppointmentsByStatus");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        Appointment a = buildAppointment(clientId);
        a.setStatus("payment_submitted");
        instance.saveAppointment(a);

        java.util.List<Appointment> result = instance.getAppointmentsByStatus("payment_submitted");
        assertFalse(result.isEmpty());
    }

    @Test
    public void testHasPendingDirectHire() {
        System.out.println("hasPendingDirectHire");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();

        // No direct hire exists yet for a random technician id
        boolean result = instance.hasPendingDirectHire(clientId, 999999);
        assertFalse("Should be false when no direct hire exists", result);
    }

    @Test
    public void testGetPendingPayments() {
        System.out.println("getPendingPayments");
        int clientId = createTestClient();
        AppointmentDao instance = new AppointmentDao();
        Appointment a = buildAppointment(clientId);
        a.setStatus("awaiting_payment");
        instance.saveAppointment(a);

        java.util.List<Appointment> result = instance.getPendingPayments();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}