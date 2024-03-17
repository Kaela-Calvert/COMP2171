package UserInterface;

import Source.LinkBike;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

        readBikesFromTxt(); // Read and display bikes info

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
                String bikeIDInput = JOptionPane.showInputDialog(null, "Enter Bike ID:");
                try {
                    int bikeID = Integer.parseInt(bikeIDInput);
                    if (LinkBike.linkToBike(bikeID, stationName)) {
                        readBikesFromTxt(); // Refresh the table
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Bike ID. Please enter a valid ID.");
                }
            }
        });

        unlinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LinkBike.unlinkFromBike()) {
                    readBikesFromTxt(); // Refresh the table
                }
            }
        });

        buttonPanel.add(returnButton);
        buttonPanel.add(linkButton);
        buttonPanel.add(unlinkButton);

        frame.setVisible(true);
    }

    private void readBikesFromTxt() {
        tableModel.setRowCount(0); // Clear previous data from table
        try (Scanner scanner = new Scanner(new File("TextFiles/bikes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] bikeInfo = scanner.nextLine().split("_");
                if (bikeInfo[1].equalsIgnoreCase(stationName)) {
                    tableModel.addRow(new Object[]{bikeInfo[0], bikeInfo[1], bikeInfo[2], bikeInfo[3], bikeInfo[4],
                            bikeInfo[5], bikeInfo[6]});
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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