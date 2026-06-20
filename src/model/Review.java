package model;

public class Review {
    private int id;
    private int appointmentId;
    private int clientId;
    private int technicianId;
    private int rating;
    private String comment;
    private String createdAt;
    private String clientName;

    public int getId()             { return id; }
    public void setId(int id)      { this.id = id; }

    public int getAppointmentId()                      { return appointmentId; }
    public void setAppointmentId(int appointmentId)    { this.appointmentId = appointmentId; }

    public int getClientId()                   { return clientId; }
    public void setClientId(int clientId)      { this.clientId = clientId; }

    public int getTechnicianId()                       { return technicianId; }
    public void setTechnicianId(int technicianId)      { this.technicianId = technicianId; }

    public int getRating()                 { return rating; }
    public void setRating(int rating)      { this.rating = rating; }

    public String getComment()                 { return comment; }
    public void setComment(String comment)     { this.comment = comment; }

    public String getCreatedAt()                   { return createdAt; }
    public void setCreatedAt(String createdAt)     { this.createdAt = createdAt; }

    public String getClientName()                  { return clientName; }
    public void setClientName(String clientName)   { this.clientName = clientName; }
}
