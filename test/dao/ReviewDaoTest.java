package dao;

import model.Appointment;
import model.UserData;
import model.Review;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewDaoTest {

    private int clientId;
    private int techId;
    private int appointmentId;

    @Before
    public void setUp() {
        UserData user = new UserData("reviewClient" + System.currentTimeMillis(),
                "reviewclient" + System.currentTimeMillis() + "@fixly.com", "pass123", "test address");
        UserDao userDao = new UserDao();
        userDao.createUser(user);
        clientId = userDao.getIdByEmail(user.getEmail());

        UserData tech = new UserData();
        tech.setUsername("reviewTech" + System.currentTimeMillis());
        tech.setEmail("reviewtech" + System.currentTimeMillis() + "@fixly.com");
        tech.setPassword("pass123");
        tech.setSpecialization("Plumber");
        TechnicianDao techDao = new TechnicianDao();
        techDao.createTechnicianWithCert(tech, "/certs/test.pdf");
        techId = techDao.getIdByEmail(tech.getEmail());

        Appointment a = new Appointment();
        a.setClientId(clientId);
        a.setServiceType("Plumber");
        a.setDate("2026-07-01");
        a.setTime("10:00 AM");
        a.setNotes("Test");
        a.setAddress("Test address");
        a.setStatus("completed");
        a.setPaymentMethod("cash");
        AppointmentDao apptDao = new AppointmentDao();
        apptDao.saveAppointment(a);
        java.util.List<Appointment> appts = apptDao.getAppointmentsByUser(clientId);
        appointmentId = appts.get(appts.size() - 1).getId();
    }

    @Test
    public void testSubmitReview() {
        System.out.println("submitReview");
        ReviewDao instance = new ReviewDao();
        boolean result = instance.submitReview(appointmentId, clientId, techId, 5, "Great service!");
        assertTrue("Review submission should succeed", result);
    }

    @Test
    public void testHasReviewed() {
        System.out.println("hasReviewed");
        ReviewDao instance = new ReviewDao();
        instance.submitReview(appointmentId, clientId, techId, 4, "Good job");

        boolean result = instance.hasReviewed(appointmentId, clientId);
        assertTrue("Should be true after review submitted", result);
    }

    @Test
    public void testGetReviewsForTechnician() {
        System.out.println("getReviewsForTechnician");
        ReviewDao instance = new ReviewDao();
        instance.submitReview(appointmentId, clientId, techId, 5, "Excellent");

        java.util.List<Review> result = instance.getReviewsForTechnician(techId);
        assertFalse(result.isEmpty());
        assertEquals(5, result.get(0).getRating());
    }

    @Test
    public void testGetAverageRating() {
        System.out.println("getAverageRating");
        ReviewDao instance = new ReviewDao();
        instance.submitReview(appointmentId, clientId, techId, 4, "Solid");

        double result = instance.getAverageRating(techId);
        assertTrue("Average rating should be greater than 0", result > 0);
    }

    @Test
    public void testGetAllReviews() {
        System.out.println("getAllReviews");
        ReviewDao instance = new ReviewDao();
        java.util.List<Review> result = instance.getAllReviews();
        assertNotNull(result);
    }

    @Test
    public void testDeleteReview() {
        System.out.println("deleteReview");
        ReviewDao instance = new ReviewDao();
        instance.submitReview(appointmentId, clientId, techId, 3, "Average");

        java.util.List<Review> reviews = instance.getReviewsForTechnician(techId);
        int reviewId = reviews.get(0).getId();

        boolean result = instance.deleteReview(reviewId);
        assertTrue("Delete should succeed", result);
    }
}