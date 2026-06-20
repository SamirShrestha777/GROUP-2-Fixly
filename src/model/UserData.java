package model;

public class UserData {
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String address;
    private String specialization;
    private String role;
    private String department;
    private String empId;
    private String certificationPath;

    public UserData() {}

    public UserData(String username, String email, String password, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public int getId() { return user_id; }
    public void setId(int id) { this.user_id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }
    public String getCertificationPath() { return certificationPath; }
    public void setCertificationPath(String certificationPath) { this.certificationPath = certificationPath; }
}