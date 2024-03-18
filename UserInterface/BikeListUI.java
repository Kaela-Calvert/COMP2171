package UserInterface;

import Controllers.BikeController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BikeListUI {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private String stationName;

    public BikeListUI(String stationName, JFrame parentFrame) {
        this.stationName = stationName;
        frame = new JFrame("Bike List - " + stationName);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        // Main panel to hold table and buttons
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // Table setup
        String[] columns = {"Bicycle ID", "Location", "User Rating", "Condition", "Availability", "Price", "Late Fee"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable bikeTable = new JTable(tableModel);
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
                if (!BikeController.isUserLinked()) {
                    String bikeIDInput = JOptionPane.showInputDialog(null, "Enter Bike ID:");
                    try {
                        int bikeID = Integer.parseInt(bikeIDInput);
                        if (BikeController.linkToBike(bikeID, stationName)) {
                            JOptionPane.showMessageDialog(null, "You are sucessfully linke to: " + BikeController.getLinkedBikeID() );
                            loadBikesIntoTable(); // Refresh the table
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Bike ID or bike is not available: " + bikeID);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Bike ID. Please enter a valid ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You are already linked to Bike ID: " + BikeController.getLinkedBikeID() +
                            ". Please unlink before linking to a new bike.");
                }
            }
        });
        

        unlinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BikeController.unlinkFromBike()) {
                    loadBikesIntoTable(); // Refresh the table
                } else {
                    JOptionPane.showMessageDialog(null, "You are not currently linked to any bike.");
                }
            }
        });

        buttonPanel.add(returnButton);
        buttonPanel.add(linkButton);
        buttonPanel.add(unlinkButton);

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