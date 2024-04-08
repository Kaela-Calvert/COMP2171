package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controllers.RateBikeController;

public class RateBikeUI extends JFrame {
    private JPanel mainPanel;
    private JTextField bikeid;
    private JTextField rate;
    private JTextArea description;
    private JButton submitbutton;
    private JButton returnbutton;

    public RateBikeUI() {
        setTitle("Rate Bike");
        setBounds(300, 90, 1200, 600);
        setResizable(false);
        setLayout(new BorderLayout());

        // Create a panel to hold the image
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        add(imgPanel, BorderLayout.WEST);

        // Load and display the image
        ImageIcon imageIcon = new ImageIcon("Images/Rate.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imgPanel.add(imageLabel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(51, 102, 153));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel bikeLabel = new JLabel("Bike ID Number:");
        bikeLabel.setForeground(Color.WHITE);
        bikeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(bikeLabel, gbc);

        gbc.gridx = 1;
        bikeid = new JTextField(20);
        bikeid.setBackground(new Color(240, 240, 240));
        bikeid.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(bikeid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel rateLabel = new JLabel("Rate (0-5):");
        rateLabel.setForeground(Color.WHITE);
        rateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(rateLabel, gbc);

        gbc.gridx = 1;
        rate = new JTextField(20);
        rate.setBackground(new Color(240, 240, 240));
        rate.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(rate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel descripLabel = new JLabel("Description:");
        descripLabel.setForeground(Color.WHITE);
        descripLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(descripLabel, gbc);

        gbc.gridx = 1;
        description = new JTextArea(5, 20);
        description.setBackground(new Color(240, 240, 240));
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(description);
        mainPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        submitbutton = new JButton("Rate Bike");
        submitbutton.setBackground(new Color(51, 153, 102));
        submitbutton.setForeground(Color.WHITE);
        submitbutton.setFont(new Font("Arial", Font.BOLD, 18));
        submitbutton.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(submitbutton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        returnbutton = new JButton("Return to Menu");
        returnbutton.setBackground(new Color(51, 153, 102));
        returnbutton.setForeground(Color.WHITE);
        returnbutton.setFont(new Font("Arial", Font.BOLD, 18));
        returnbutton.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(returnbutton, gbc);

        submitbutton.addActionListener(new RateBikeListener());
        returnbutton.addActionListener(new ReturntoMainMenu());

        setVisible(true);
    }

    private class RateBikeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String bikeID = bikeid.getText();
            String rating = rate.getText();
            String desc = description.getText();

            RateBikeController rateBikeController = new RateBikeController();
            boolean success = rateBikeController.rateBike(bikeID, rating, desc);

            if (success) {
                JOptionPane.showMessageDialog(null, "Bike rated successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to rate bike. Please try again.");
            }
        }
    }

    private class ReturntoMainMenu implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
            MainPage main =new MainPage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RateBikeUI().setVisible(true);
        });
    }
}

