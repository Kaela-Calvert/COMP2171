package UserInterface;

import Controllers.PaymentController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PaymentPlanUI extends JFrame {

    private PaymentController controller;
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;
    private JButton saveButton;
    private JButton backButton;
    private JLabel imageLabel;

    public PaymentPlanUI(PaymentController controller) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        this.controller = controller;

        setTitle("Add Payment Plan");
        setBounds(300, 100, 1100, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Image setup
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Images/Payment.jpg")); // Replace with the actual path to your image
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, BorderLayout.WEST);

        mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        idField = new JTextField();
        mainPanel.add(new JLabel("User ID (6200000000):"));
        mainPanel.add(idField);

        cardNumberField = new JTextField();
        mainPanel.add(new JLabel("Card Number (12-19 digits):"));
        mainPanel.add(cardNumberField);

        expiryDateField = new JTextField();
        mainPanel.add(new JLabel("Expiry Date (xx/xx):"));
        mainPanel.add(expiryDateField);

        cvvField = new JTextField();
        mainPanel.add(new JLabel("CVV (xxx):"));
        mainPanel.add(cvvField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel);

        // Button listeners
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = idField.getText();
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                String cvv = cvvField.getText();

                if (controller.validateUserId(userId) && controller.validatePaymentInfo(userId, cardNumber, expiryDate, cvv)) {
                    controller.savePaymentInformation(userId, cardNumber, expiryDate, cvv);
                    showMessageDialog("Payment information saved successfully.You may now proceed with bike selection");
                    dispose();
                    MainPage page = new MainPage();
                } else {
                    showErrorDialog("Invalid user ID or payment information. Please check and try again.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPage page = new MainPage();
            }
        });

        setVisible(true);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create a PaymentController instance
        PaymentController controller = new PaymentController();

        // Example usage: create PaymentPlanUI with controller
        SwingUtilities.invokeLater(() -> new PaymentPlanUI(controller));
    }
}