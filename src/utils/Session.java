package utils;

public class Session {
    private static int userId;
    private static String email;
    private static String role;
    private static String specialization;

    public static int getUserId()               { return userId; }
    public static void setUserId(int id)        { userId = id; }

    public static String getEmail()             { return email; }
    public static void setEmail(String e)       { email = e; }

    public static String getRole()              { return role; }
    public static void setRole(String r)        { role = r; }

    public static String getSpecialization()        { return specialization; }
    public static void setSpecialization(String s)  { specialization = s; }
}