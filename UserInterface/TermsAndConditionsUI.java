package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TermsAndConditionsUI extends JFrame {
    private static boolean accepted = false; // Field to track acceptance

    public TermsAndConditionsUI() {
        setTitle("Terms and Conditions");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea termsAndConditionsArea = new JTextArea();
        termsAndConditionsArea.setEditable(false);
        termsAndConditionsArea.setText("Terms and Conditions:\n\n" +
                "1. Wear a helmet at all times.\n" +
                "2. Obey all traffic rules and regulations.\n" +
                "3. Do not ride under the influence of alcohol or drugs.\n" +
                "4. Return the bike in the same condition as when you rented it.\n" +
                "5. Any damage or loss of the bike will result in additional charges.\n" +
                "6. Do not modify or tamper with the bike in any way.\n" +
                "7. Bikes are not allowed on sidewalks or pedestrian areas.\n" +
                "8. Bikes must be parked in designated bike racks or parking areas.\n" +
                "9. Do not lend or sub-rent the bike to others.\n" +
                "10. Report any issues or malfunctions with the bike immediately.\n" +
                "11. Riders under the age of 16 must be accompanied by an adult.\n" +
                "12. Riders assume all risks and liabilities associated with the bike rental.\n");
        JScrollPane scrollPane = new JScrollPane(termsAndConditionsArea);
        add(scrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set accepted to true when the Accept button is clicked
                accepted = true;
                // Close the terms and conditions UI
                dispose();
            }
        });
        buttonPanel.add(acceptButton);
    }

    // Method to check if the terms were accepted
    public static boolean isAccepted() {
        return accepted;
    }
}
