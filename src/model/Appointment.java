package model;

public class Appointment {
    private int id;
    private int clientId;
    private int technicianId;
    private String serviceType;
    private String date;
    private String time;
    private String notes;
    private String address;
    private String status;
    private String clientName;
    private String email;
    private String paymentMethod;

    public int getId()                          { return id; }
    public void setId(int id)                   { this.id = id; }
    public int getClientId()                    { return clientId; }
    public void setClientId(int id)             { this.clientId = id; }
    public int getTechnicianId()                { return technicianId; }
    public void setTechnicianId(int id)         { this.technicianId = id; }
    public String getServiceType()              { return serviceType; }
    public void setServiceType(String s)        { this.serviceType = s; }
    public String getDate()                     { return date; }
    public void setDate(String date)            { this.date = date; }
    public String getTime()                     { return time; }
    public void setTime(String time)            { this.time = time; }
    public String getNotes()                    { return notes; }
    public void setNotes(String notes)          { this.notes = notes; }
    public String getAddress()                  { return address; }
    public void setAddress(String address)      { this.address = address; }
    public String getStatus()                   { return status; }
    public void setStatus(String status)        { this.status = status; }
    public String getClientName()               { return clientName; }
    public void setClientName(String name)      { this.clientName = name; }
    public String getEmail()                    { return email; }
    public void setEmail(String email)          { this.email = email; }
    public String getPaymentMethod()            { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}