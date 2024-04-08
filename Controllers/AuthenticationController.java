package Controllers;

import Source.Admin;
import Source.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Security.Authentication;

public class AuthenticationController {
    private List<User> users = new ArrayList<>();
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

    public boolean isValidUwiId(String id) {
        // Implement your logic to check if the provided ID is a valid UWI ID
        // For simplicity, let's assume that any non-empty string is a valid UWI ID
        return !id.isEmpty();
    }

    public boolean isUserExists(String id) {
        for (User user : users) {
            if (user.getid().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void sendVerificationEmail(String id) {
        // Implement your logic to send a verification email to the user's UWI email address
        // For simplicity, we'll generate a random verification token and print it to the console
        String verificationToken = generateVerificationToken();
        System.out.println("Verification token for " + id + ": " + verificationToken);
    }

    public boolean verifyToken(String id, String token) {
        // Implement your logic to verify the provided token for the given ID
        // For simplicity, we'll generate a new verification token and compare it with the provided token
        String verificationToken = generateVerificationToken();
        return token.equals(verificationToken);
    }

    // public User createUser(String id) {
    //     // Implement your logic to create a new user with the provided ID
    //     User newUser = new User(id, generatePassword());
    //     users.add(newUser);
    //     return newUser;
    // }

    // public boolean authenticateUser(String id, String password, boolean isAdmin) {
    //     // Implement your logic to authenticate the user with the provided credentials
    //     for (User user : users) {
    //         if (user.getId().equals(id) && user.getPassword().equals(password)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    private String generateVerificationToken() {
        // Generate a random verification token for simplicity
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String generatePassword() {
        // Generate a random password for simplicity
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }
}
