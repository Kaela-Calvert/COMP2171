package UserInterface;

import Controllers.AuthenticationController;
import Source.Admin;
import Source.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class LoginUI extends JFrame {
    private JPanel mainPanel;
    private JPanel imgPanel = new JPanel();
    private JTextField idText = new JTextField(20);
    private JPasswordField passwordText = new JPasswordField(20);
    private JLabel passwordLabel = new JLabel("Password: ");
    private JLabel idLabel = new JLabel("ID:");
    private JButton loginButton;
    private JButton signUpButton;
    private JCheckBox adminCheckBox = new JCheckBox("Administrator");
    private AuthenticationController authController;

    public LoginUI() {
        try {
            // Set the Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        setTitle("UWI ON WHEELS");
        setBounds(300, 90, 1000, 600);
        setResizable(false);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(51, 102, 153));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        adminCheckBox.setForeground(new Color(51, 102, 153));
        adminCheckBox.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(adminCheckBox, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(idLabel, gbc);

        gbc.gridx = 2;
        idText.setBackground(new Color(240, 240, 240));
        idText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(idText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 2;
        passwordText.setBackground(new Color(240, 240, 240));
        passwordText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(passwordText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(51, 153, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(loginButton, gbc);

        // gbc.gridy = 4;
        // signUpButton = new JButton("Sign Up");
        // signUpButton.setBackground(new Color(51, 153, 102));
        // signUpButton.setForeground(Color.WHITE);
        // signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        // signUpButton.setPreferredSize(new Dimension(300, 50));
        // mainPanel.add(signUpButton, gbc);

        loginButton.addActionListener(new LoginListener());
        // signUpButton.addActionListener(new SignUpListener());

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("Images/Login.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension size = new Dimension(500, 500);
        picLabel.setPreferredSize(size);
        imgPanel.add(picLabel);
        add(imgPanel, BorderLayout.WEST);

        setVisible(true);

        authController = new AuthenticationController();
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id = idText.getText();
            String password = new String(passwordText.getPassword());
            boolean isAdmin = adminCheckBox.isSelected();

            boolean isAuthenticated = authController.authenticateUser(id, password, isAdmin);

            if (isAuthenticated) {
                if (isAdmin) {
                    // Open admin menu
                    dispose();
                    MainPageAdmin adminMenuUI = new MainPageAdmin();
                    adminMenuUI.setVisible(true);
                } else {
                    // Open user menu
                    dispose();
                    MainPage userMenuUI = new MainPage();
                    userMenuUI.setVisible(true);
                }

                // Close the current login screen after navigating to the menu screen
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
            }
        }
    }

    private class SignUpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Open sign-up screen
            // dispose();
            // SignUpUI signUpUI = new SignUpUI(authController);
            // signUpUI.setVisible(true);
        }
    }
}