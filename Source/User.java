package Source;

public class User {
    private String fname;
    private String lname;
    private String id;
    private String email;
    private String password;
    private PaymentPlan paymentPlan;

    public User(String fname, String lname, String id, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.email = email;
        this.password = password;
        this.paymentPlan = null; // Initially, the user does not have a payment plan
    }

    // Getters and setters for the fields
    public User() {
        // TODO Auto-generated constructor stub
    }

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

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public boolean hasPaymentPlan() {
        return paymentPlan != null;
    }
}