package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controllers.ReportController;

public class ReportUI extends JFrame {
    private JPanel mainPanel;
    private JTextField personNameField, personIDField, personContactField;
    private JTextField descriptionField, locationField;
    private JTextField bikeIDField;
    private JTextField witnessNameField, witnessContactField;
    private JTextField dateTimeField;
    private JButton makeReportButton;
    private JButton returnButton;

    public ReportUI() {
        setTitle("Incident Report");
        setBounds(300, 90, 800, 900);
        setResizable(false);
        setLayout(new BorderLayout());

        // Load and display the image
        ImageIcon imageIcon = new ImageIcon("Images/Report.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns, variable rows
        mainPanel.setBackground(new Color(51, 102, 153));
        add(mainPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Person Name:");
        nameLabel.setForeground(Color.WHITE);
        mainPanel.add(nameLabel);
        personNameField = new JTextField();
        mainPanel.add(personNameField);

        JLabel idLabel = new JLabel("Person ID:");
        idLabel.setForeground(Color.WHITE);
        mainPanel.add(idLabel);
        personIDField = new JTextField();
        mainPanel.add(personIDField);

        JLabel contactLabel = new JLabel("Contact xxx-xxx-xxxx:");
        contactLabel.setForeground(Color.WHITE);
        mainPanel.add(contactLabel);
        personContactField = new JTextField();
        mainPanel.add(personContactField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setForeground(Color.WHITE);
        mainPanel.add(descriptionLabel);
        descriptionField = new JTextField();
        mainPanel.add(descriptionField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setForeground(Color.WHITE);
        mainPanel.add(locationLabel);
        locationField = new JTextField();
        mainPanel.add(locationField);

        JLabel bikeIDLabel = new JLabel("Bike ID:");
        bikeIDLabel.setForeground(Color.WHITE);
        mainPanel.add(bikeIDLabel);
        bikeIDField = new JTextField();
        mainPanel.add(bikeIDField);

        JLabel witnessNameLabel = new JLabel("Witness Name:");
        witnessNameLabel.setForeground(Color.WHITE);
        mainPanel.add(witnessNameLabel);
        witnessNameField = new JTextField();
        mainPanel.add(witnessNameField);

        JLabel witnessContactLabel = new JLabel("Witness Contact xxx-xxx-xxxx:");
        witnessContactLabel.setForeground(Color.WHITE);
        mainPanel.add(witnessContactLabel);
        witnessContactField = new JTextField();
        mainPanel.add(witnessContactField);

        JLabel dateTimeLabel = new JLabel("Date and Time:");
        dateTimeLabel.setForeground(Color.WHITE);
        mainPanel.add(dateTimeLabel);
        dateTimeField = new JTextField(getCurrentDateTime());
        dateTimeField.setEditable(false);
        mainPanel.add(dateTimeField);

        makeReportButton = new JButton("Make Report");
        makeReportButton.setBackground(new Color(51, 153, 102)); 
        makeReportButton.setForeground(Color.WHITE); // White text
        makeReportButton.addActionListener(new MakeReportListener());
        mainPanel.add(makeReportButton);

        returnButton = new JButton("Return to Main Menu");
        returnButton.setBackground(new Color(51, 153, 102)); 
        returnButton.setForeground(Color.WHITE); // White text
        returnButton.addActionListener(new ReturnListener());
        mainPanel.add(returnButton);

        setVisible(true);
    }

    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    private class MakeReportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (validateFields()) {
                String personName = personNameField.getText();
                String personID = personIDField.getText();
                String personContact = personContactField.getText();
                String description = descriptionField.getText();
                String location = locationField.getText();
                String bikeID = bikeIDField.getText();
                String witnessName = witnessNameField.getText();
                String witnessContact = witnessContactField.getText();
                String dateTime = dateTimeField.getText();

                ReportController.addReportToFile(personName, personID, personContact, description, location, bikeID, witnessName, witnessContact, dateTime);

                JOptionPane.showMessageDialog(null, "Report submitted successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill out all fields and enter valid phone number!");
            }
        }
    }

    private boolean validateFields() {
        return !personNameField.getText().isEmpty() &&
               !personIDField.getText().isEmpty() &&
               !personContactField.getText().isEmpty() &&
               !descriptionField.getText().isEmpty() &&
               !locationField.getText().isEmpty() &&
               !bikeIDField.getText().isEmpty() &&
               !witnessNameField.getText().isEmpty() &&
               !witnessContactField.getText().isEmpty() &&
               isValidPhoneNumber(witnessContactField.getText());
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Add phone number format validation logic here
        // Check if the phone number is in the format xxx-xxx-xxxx
        return phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    private class ReturnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        }
    }

    private void clearFields() {
        personNameField.setText("");
        personIDField.setText("");
        personContactField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        bikeIDField.setText("");
        witnessNameField.setText("");
        witnessContactField.setText("");
        dateTimeField.setText(getCurrentDateTime());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReportUI().setVisible(true);
        });
    }
}

