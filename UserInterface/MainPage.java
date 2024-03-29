package UserInterface;

import javax.swing.*;

import Controllers.PaymentController;
import UserInterface.TermsAndConditionsUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainPage extends JFrame {
    private JPanel mainPanel;
    private JPanel imgPanel = new JPanel();

    public MainPage() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        setTitle("Main Menu");
        setBounds(300, 90, 800, 600);
        setResizable(false);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        add(mainPanel, BorderLayout.CENTER);

        // Image panel setup
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("Images/UserMenu.jpg")); // Change to your image path
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension size = new Dimension(400, 400);
        picLabel.setPreferredSize(size);
        imgPanel.add(picLabel);
        add(imgPanel, BorderLayout.NORTH);

        // Buttons panel setup
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonsPanel.setBackground(Color.LIGHT_GRAY);

        JButton rentBikeButton = new JButton("Rent Bike");
        JButton addPaymentPlanButton = new JButton("Add Payment Plan");
        JButton selectBikeButton = new JButton("Select Bike");
        JButton rateExperienceButton = new JButton("Rate Experience");
        JButton generateReportButton = new JButton("Generate Report");
        JButton logoutButton = new JButton("Logout");

        rentBikeButton.setPreferredSize(new Dimension(180, 40));
        addPaymentPlanButton.setPreferredSize(new Dimension(180, 40));
        selectBikeButton.setPreferredSize(new Dimension(180, 40));
        rateExperienceButton.setPreferredSize(new Dimension(180, 40));
        generateReportButton.setPreferredSize(new Dimension(180, 40));
        logoutButton.setPreferredSize(new Dimension(180, 40));

        buttonsPanel.add(rentBikeButton);
        buttonsPanel.add(addPaymentPlanButton);
        buttonsPanel.add(selectBikeButton);
        buttonsPanel.add(rateExperienceButton);
        buttonsPanel.add(generateReportButton);
        buttonsPanel.add(logoutButton);

        mainPanel.add(buttonsPanel);

        // Button listeners
        rentBikeButton.addActionListener(new RentBikeListener());
        addPaymentPlanButton.addActionListener(new AddPaymentPlanListener());
        selectBikeButton.addActionListener(new SelectBikeListener());
        rateExperienceButton.addActionListener(new RateExperienceListener());
        generateReportButton.addActionListener(new GenerateReportListener());
        logoutButton.addActionListener(new LogoutListener());

        setVisible(true);
    }

    // Action listeners for buttons
    private class RentBikeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Create an instance of RentBike class from Source package
            // Source.RentBike rentBike = new Source.RentBike();
            // Perform any additional actions as needed
        }
    }

    private class AddPaymentPlanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Create an instance of AddPaymentPlan class from Source package
            dispose();
            PaymentController controller = new PaymentController();
            UserInterface.PaymentPlanUI addPaymentPlan = new UserInterface.PaymentPlanUI(controller);
            // Perform any additional actions as needed
        }
    }

    
private class SelectBikeListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        dispose();
        // Show the Terms and Conditions UI
        TermsAndConditionsUI termsAndConditionsUI = new TermsAndConditionsUI();
        termsAndConditionsUI.setVisible(true);

        // Add a window listener to wait for the user to accept the terms
        termsAndConditionsUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Proceed with selecting the bike only if the user accepted the terms
                // if (TermsAndConditionsUI.isAccepted()) {
                    boolean truth = true;
                if (truth) {
                    // Create an instance of SelectBikeUI class and show it
                    SelectBikeUI selectBike = new SelectBikeUI();
                    selectBike.setVisible(true);
                } else {
                    // Optionally, handle the case where the user did not accept the terms
                    // For example, display a message or take appropriate action
                    JOptionPane.showMessageDialog(null, "You must accept the terms and conditions to proceed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
    

    private class RateExperienceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Create an instance of RateExperience class from Source package
            // Source.RateExperience rateExperience = new Source.RateExperience();
            // Perform any additional actions as needed
        }
    }

    private class GenerateReportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Create an instance of GenerateReport class from Source package
            // Source.GenerateReport generateReport = new Source.GenerateReport();
            // Perform any additional actions as needed
        }
    }

    private class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Handle logout action, e.g., close the application or go back to the login screen
            dispose(); // Close the current window
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPage().setVisible(true);
        });
    }
}

