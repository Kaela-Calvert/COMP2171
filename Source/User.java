package Source;

public class User {
    private String fname;
    private String lname;
    private String id;
    private String email;
    private String password;

    public User(String fname, String lname, String id, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getters and setters for the fields

    public String getfname() {
        return fname;
    }

    public String getlname() {
        return lname;
    }

    public String getid() {
        return id;
    }

    public String getemail() {
        return email;
    }

    public String getpassword() {
        return password;
    }
}