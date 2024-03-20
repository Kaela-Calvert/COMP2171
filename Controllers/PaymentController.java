package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Source.PaymentPlan;
import Source.User;

public class PaymentController {
    private static User currentUser;

    public PaymentController(){
        //some filler
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }


    public boolean validateUserId(String userId) {
        // Implement logic to validate the user ID
        // For example, check if the ID is not empty and follows a specific format
        return !userId.isEmpty() && userId.matches("[A-Za-z0-9]+");
    }

    public boolean validatePaymentInfo(String userId, String cardNumber, String expiryDate, String cvv) {
        // Validate user ID
        if (!validateUserId(userId)) {
            return false;
        }

        // Validate card number
        if (!isValidCardNumber(cardNumber)) {
            return false;
        }

        // Validate expiry date
        if (!isValidExpiryDate(expiryDate)) {
            return false;
        }

        // Validate CVV
        if (!isValidCVV(cvv)) {
            return false;
        }

        return true;
    }

    public static boolean checkPaymentPlan(String userId) {
        if (userId != null) {
            try (Scanner scanner = new Scanner(new File("TextFiles/paymentdata.txt"))) {
                while (scanner.hasNextLine()) {
                    String userData = scanner.nextLine();
                    if (userData.contains(userId)) {
                        // User ID found in paymentdata.txt
                        // Return true regardless of payment plan status
                        return true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        // User not found or no payment plan
        return false;
    }


    public void savePaymentInformation(String userId, String cardNumber, String expiryDate, String cvv) {
        if (validatePaymentInfo(userId, cardNumber, expiryDate, cvv)) {
            PaymentPlan paymentPlan = new PaymentPlan(cardNumber, expiryDate, cvv);
    
            // Format the payment information
            String paymentInfo = userId + " " + cardNumber + " " + expiryDate + " " + cvv;
    
            // Save payment information to the paymentdata.txt file
            try (PrintWriter writer = new PrintWriter(new FileWriter("TextFiles/paymentdata.txt", true))) {
                writer.println(paymentInfo);
                System.out.println("Payment information saved successfully for user ID: " + userId);
            } catch (IOException e) {
                System.out.println("Error saving payment information.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid payment information or user ID. Please check and try again.");
        }
    }
    

    private boolean isValidCardNumber(String cardNumber) {
        // Check if the card number is between 12 and 19 digits long
        if (cardNumber.length() < 12 || cardNumber.length() > 19) {
            return false;
        }

        // Check if the card number consists of only digits
        return cardNumber.matches("\\d+");
    }

    private boolean isValidExpiryDate(String expiryDate) {
        // Check if the expiry date is in the format MM/yy and is a valid future date
        // For simplicity, we'll assume any date in the future is valid
        String[] parts = expiryDate.split("/");
        if (parts.length != 2) {
            return false;
        }
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        return month >= 1 && month <= 12 && year >= 22; // Assuming year 22 is the minimum valid year
    }

    private boolean isValidCVV(String cvv) {
        // Check if the CVV is 3 or 4 digits long
        return cvv.length() == 3 || cvv.length() == 4;
    }
}
