package Security;

import Source.Admin;
import Source.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Authentication {
    private static ArrayList<User> users = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    public static final String file = "TextFiles/userdata.txt";

    public Authentication() {
        readUserData(file);
    }

    private void readUserData(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(" ");
                if (userData.length == 5) {
                    String fname = userData[0];
                    String lname = userData[1];
                    String id = userData[2];
                    String email = userData[3];
                    String password = userData[4];
                    users.add(new User(fname,lname,id,email,password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String id, String password, boolean isAdmin) {
        if (isAdmin) {
            // Admin authentication logic (replace with actual admin credentials check)
            return id.equals("admin") && password.equals("adminpass");
        } else {
            // User authentication logic
            for (User user : users) {
                if (user.getid().equals(id) && user.getpassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
