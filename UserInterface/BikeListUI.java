package UserInterface;

import Controllers.BikeController;
import Controllers.PaymentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class BikeListUI {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private String stationName;
    private JTable bikeTable; // Added JTable instance for access in methods

    public BikeListUI(String stationName, JFrame parentFrame) {
        this.stationName = stationName;
        frame = new JFrame("Bike List - " + stationName);
        frame.setSize(800, 600); // Set a fixed size for the frame

        // Main panel to hold table and buttons
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // Table setup
        String[] columns = {"Bicycle ID", "Location", "User Rating", "Condition", "Availability", "Price", "Late Fee"};
        tableModel = new DefaultTableModel(columns, 0);
        bikeTable = new JTable(tableModel); // Initialize the JTable
        JScrollPane scrollPane = new JScrollPane(bikeTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadBikesIntoTable(); // Read and display bikes info

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Return to Menu button
        JButton returnButton = new JButton("Return to Menu");
        JButton linkButton = new JButton("Get Linked");
        JButton unlinkButton = new JButton("Get Unlinked");

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });

        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter their ID number
                String idInput = JOptionPane.showInputDialog(null, "Enter your ID number:");
        
                // Check if the user entered an ID number
                if (idInput != null && !idInput.isEmpty()) {
                    String idnum = idInput.trim(); // Trim any leading or trailing whitespace
        
                    // Check if the user has a payment plan
                    boolean hasPaymentPlan = PaymentController.checkPaymentPlan(idnum);
                    if (hasPaymentPlan) {
                        if (BikeController.getLinkedBikeID() != -1) {
                            int choice = JOptionPane.showConfirmDialog(null, "You are already linked to a bike. Do you want to unlink the current bike?", "Confirm Unlink", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                boolean unlinked = BikeController.unlinkFromBike();
                                if (unlinked) {
                                    JOptionPane.showMessageDialog(null, "Current bike unlinked successfully.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Failed to unlink the current bike.", "Error", JOptionPane.ERROR_MESSAGE);
                                    SwingUtilities.invokeLater(() -> new BikeListUI(stationName, parentFrame));
                                }
                            } else {
                                SwingUtilities.invokeLater(() -> new BikeListUI(stationName, parentFrame));
                            }
                        }
        
                        // Prompt the user to enter the bike ID
                        String bikeIdInput = JOptionPane.showInputDialog(null, "Enter the bike ID you want to link:");
        
                        if (bikeIdInput != null && !bikeIdInput.isEmpty()) {
                            try {
                                int bikeId = Integer.parseInt(bikeIdInput.trim()); // Parse the bike ID as an integer
        
                                // Get rental duration from the user
                                String durationInput = JOptionPane.showInputDialog(null, "Enter the rental duration in hours:");
        
                                if (durationInput != null && !durationInput.isEmpty()) {
                                    try {
                                        int rentalHours = Integer.parseInt(durationInput.trim()); // Parse rental hours as an integer
        
                                        // Hide the BikeListUI
                                        frame.dispose();
        
                                        // Proceed with linking the bike
                                        boolean linked = BikeController.linkToBike(bikeId, stationName);
                                        if (linked) {
                                            // Get current time for rental invoice
                                            LocalDateTime startTime = LocalDateTime.now();
                                            LocalDateTime endTime = startTime.plusHours(rentalHours); // Set rental end time based on user input
        
                                            // Display rental invoice
                                            RentalInvoiceUI invoiceUI = new RentalInvoiceUI(idnum, startTime, endTime);
                                            invoiceUI.setVisible(true);
        
                                            JOptionPane.showMessageDialog(null, "Bike linked successfully.");
                                            loadBikesIntoTable(); // Refresh the table after linking
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Bike Unavailable....Failed to link bike.", "Error", JOptionPane.ERROR_MESSAGE);
                                            SwingUtilities.invokeLater(() -> new BikeListUI(stationName, parentFrame));
                                        }
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Invalid rental duration. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid rental duration.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid bike ID. Please enter an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a valid bike ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You must have a payment plan before linking a bike.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        

        buttonPanel.add(returnButton);
        buttonPanel.add(linkButton);
        buttonPanel.add(unlinkButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application on frame close
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setResizable(false); // Disable frame resizing
        frame.setVisible(true);
    }

    private void loadBikesIntoTable() {
        tableModel.setRowCount(0); // Clear previous data from table
        List<BikeController.BikeData> bikes = BikeController.getBikesForStation(stationName);
        for (BikeController.BikeData bike : bikes) {
            tableModel.addRow(new Object[]{bike.getBikeID(), bike.getLocation(), bike.getUserRating(), bike.getCondition(),
                    bike.getAvailability(), bike.getPrice(), bike.getLateFee()});
        }
    }

    public static void main(String[] args) {
        // Example usage: create BikeListUI with station name "SciTech" and parent frame
        JFrame parentFrame = new JFrame();
        parentFrame.setSize(300, 200);
        parentFrame.setVisible(true);
        SwingUtilities.invokeLater(() -> new BikeListUI("SciTech", parentFrame));
    }
}
