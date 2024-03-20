package Controllers;

import UserInterface.RentalInvoiceUI;
import UserInterface.TermsAndConditionsUI;
import Source.User;

import javax.swing.*;
import java.time.LocalDateTime;

public class RentingController {
    private User currentUser;

    public RentingController() {
        // Initialize currentUser as needed, such as fetching from authentication
    }

    public void rentBike(int bikeId, String stationName) {
        TermsAndConditionsUI termsAndConditionsUI = new TermsAndConditionsUI();
        termsAndConditionsUI.setVisible(true);
    }

    public void handleRentalDuration() {
        String durationInput = JOptionPane.showInputDialog(null, "Enter the rental duration (e.g., 1h, 2h, 3d):");

        if (durationInput != null && !durationInput.isEmpty()) {
            try {
                long durationValue = Long.parseLong(durationInput.replaceAll("[^\\d]", ""));
                String durationUnit = durationInput.replaceAll("[\\d]", "").toLowerCase();

                LocalDateTime startTime = LocalDateTime.now();
                LocalDateTime endTime;

                if (durationUnit.equals("h")) {
                    endTime = startTime.plusHours(durationValue);
                } else if (durationUnit.equals("d")) {
                    endTime = startTime.plusDays(durationValue);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid duration format. Please enter a value followed by 'h' for hours or 'd' for days.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Generate and display the rental invoice
                String userid = currentUser.getid();
                RentalInvoiceUI invoiceUI = new RentalInvoiceUI(userid, startTime, endTime);
                invoiceUI.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid duration format. Please enter a value followed by 'h' for hours or 'd' for days.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
