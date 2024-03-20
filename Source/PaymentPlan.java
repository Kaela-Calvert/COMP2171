package Source;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentPlan {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public PaymentPlan(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public PaymentPlan() {
        // Initialize payment plan
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("paymentdata.txt", true))) {
            writer.println("Card Number: " + cardNumber);
            writer.println("Expiry Date: " + expiryDate);
            writer.println("CVV: " + cvv);
            writer.println("--------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
