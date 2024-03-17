package Source;

public class Admin {
    private String fname;
    private String lname;
    private String id;
    private String email;
    private String password;
    private String accesskey;

    public Admin(String fname, String lname, String id, String email, String password, String accesskey) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.email = email;
        this.password = password;
        this.accesskey = accesskey;
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

    public String getAccesskey() {
        return accesskey;
    }
}