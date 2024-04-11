package UserInterface;

import Controllers.RemoveUserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RemoveUserUI extends JFrame {
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JPanel buttonPanel;
    private JTextField userIdField;
    private JButton removeButton;
    private JButton backButton;
    private RemoveUserController removeController;

    public RemoveUserUI() {
        setTitle("Remove User");
        setBounds(300, 90, 1000, 600);
        setResizable(false);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(51, 102, 153));
        add(mainPanel, BorderLayout.CENTER);

        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(51, 102, 153));
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File("Images/Remove.png"));
            imageLabel.setIcon(new ImageIcon(image));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imagePanel.add(imageLabel);
        mainPanel.add(imagePanel, BorderLayout.WEST);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 102, 153));
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel userIdLabel = new JLabel("Remove user with ID:");
        userIdLabel.setForeground(Color.WHITE);
        userIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(userIdLabel, gbc);

        userIdField = new JTextField(20);
        userIdField.setBackground(new Color(240, 240, 240));
        userIdField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        // gbc.anchor = GridBagConstraints.WEST;
        buttonPanel.add(userIdField, gbc);

        removeButton = new JButton("Remove User");
        removeButton.setBackground(new Color(51, 153, 102));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.BOLD, 18));
        removeButton.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(removeButton, gbc);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(51, 153, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(300, 50));
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(backButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.EAST);

        removeController = new RemoveUserController();
        removeButton.addActionListener(new RemoveUserListener());
        backButton.addActionListener(new BackListener());

        setVisible(true);
    }

    private class RemoveUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userId = userIdField.getText();
            if (removeController.removeUser(userId)) {
                JOptionPane.showMessageDialog(null, "User successfully removed.");
                dispose();
                MainPageAdmin mainAdmin = new MainPageAdmin();
                mainAdmin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to remove user. Please try again.");
            }
        }
    }

    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainPageAdmin mainAdmin = new MainPageAdmin();
            mainAdmin.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RemoveUserUI().setVisible(true);
        });
    }
    
}
