package UserInterface;

import Controllers.CostController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentalInvoiceUI extends JFrame {
    public RentalInvoiceUI(String userId, LocalDateTime startTime, LocalDateTime endTime) {
        setTitle("Rental Invoice");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Image
        ImageIcon logoIcon = new ImageIcon("Images/Invoice.jpg");
        JLabel logoLabel = new JLabel(logoIcon);
        mainPanel.add(logoLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // String userName = userController.getUserName(userId);

        JLabel userLabel = new JLabel("User: " + userId);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(userLabel, gbc);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        JLabel startTimeLabel = new JLabel("Start Time: " + startTime.format(formatter));
        gbc.gridy = 1;
        contentPanel.add(startTimeLabel, gbc);

        JLabel endTimeLabel = new JLabel("End Time: " + endTime.format(formatter));
        gbc.gridy = 2;
        contentPanel.add(endTimeLabel, gbc);

        // Calculate rental duration in hours
        long rentalDurationHours = Duration.between(startTime, endTime).toHours();

        // Calculate the cost
        double cost = CostController.calculateCost(rentalDurationHours);

        JLabel costLabel = new JLabel("Cost: $" + String.format("%.2f", cost));
        gbc.gridy = 3;
        contentPanel.add(costLabel, gbc);

        // Add a button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton returnToMainMenuButton = new JButton("Return to Main Menu");
        buttonPanel.add(returnToMainMenuButton);
        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle return to main menu action here
                // For example, you can dispose of the current UI and show the main menu UI
                dispose();
                MainPage mainMenuUI = new MainPage();
                mainMenuUI.setVisible(true);
            }
        });

        pack();
    }
}