package Controllers;

import javax.swing.*;
import java.io.*;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class SignUpController {
    private static final String EMAIL_DATA_FILE = "TextFiles/emaildata.txt";
    private static final String USER_DATA_FILE = "TextFiles/userdata.txt";
    private static final String EMAIL_USERNAME = "kaelacalvert14@gmail.com"; // Replace with your email
    private static final String EMAIL_PASSWORD = "zfqv whjh vrll auju"; // Replace with your email password
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String id;

    public SignUpController(String firstName, String lastName, String id, String email, String password) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public boolean signUpUser() {
        boolean idExists = checkIdExists(id);
        if (idExists) {
            String verificationCode = generateVerificationCode();
            boolean emailSent = sendVerificationEmail(email, verificationCode);
            if (emailSent) {
                String inputCode = JOptionPane.showInputDialog("Verification code sent to your email. Enter code:");
                if (inputCode != null && inputCode.equals(verificationCode)) {
                    addUserToSystem(firstName, lastName, id, email, password);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid verification code. Please try again.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to send verification email. Please try again.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid ID.");
            return false;
        }
    }

    private boolean checkIdExists(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMAIL_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2 && parts[0].equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addUserToSystem(String firstName, String lastName, String id, String email, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.println(firstName + " " + lastName + " " + id + " " + email + " " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }

    private boolean sendVerificationEmail(String recipientEmail, String verificationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + verificationCode);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}