package Controllers;

import Source.Admin;
import Source.User;
import Security.Authentication;

public class AuthenticationController {
    private Authentication authentication;

    public AuthenticationController() {
        this.authentication = new Authentication();
    }

    public boolean authenticateUser(String id, String password, boolean isAdmin) {
        return authentication.authenticate(id, password, isAdmin);
    }

    public boolean registerUser(User user) {
        // Logic to register a new user
        return true; // Placeholder for registration success
    }

    public boolean registerAdmin(Admin admin) {
        // Logic to register a new admin
        return true; // Placeholder for registration success
    }
}
