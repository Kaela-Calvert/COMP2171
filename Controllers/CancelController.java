package Controllers;

import java.io.*;
import java.util.ArrayList;

public class CancelController {
    private static final String FILE_PATH = "TextFiles/paymentdata.txt";

    public boolean cancelPayment(String userId) {
        if (!hasPaymentPlan(userId)) {
            return false;
        }

        if (!removePaymentPlan(userId)) {
            return false;
        }

        return true;
    }

    private boolean hasPaymentPlan(String userId) {
        ArrayList<String> paymentPlans = readPaymentPlans();
        for (String plan : paymentPlans) {
            if (plan.startsWith(userId)) {
                return true;
            }
        }
        return false;
    }

    private boolean removePaymentPlan(String userId) {
        ArrayList<String> paymentPlans = readPaymentPlans();
        ArrayList<String> updatedPlans = new ArrayList<>();
        boolean found = false;
        for (String plan : paymentPlans) {
            if (!plan.startsWith(userId)) {
                updatedPlans.add(plan);
            } else {
                found = true;
            }
        }

        if (!found) {
            return false; 
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String updatedPlan : updatedPlans) {
                writer.println(updatedPlan);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ArrayList<String> readPaymentPlans() {
        ArrayList<String> paymentPlans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                paymentPlans.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentPlans;
    }
}
