package model;
import java.util.Date;
public class Appointment {

    private String email;
    private String date;
    private String time;
    private String notes;
    private String address;
    private Date appointmentDate;

    public Appointment() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


public Date getAppointmentDate() {
    return appointmentDate;
}
public void setAppointmentDate(Date appointmentDate) {
    this.appointmentDate = appointmentDate;
}
}