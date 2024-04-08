package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controllers.SignUpController;

public class SignUpUI extends JFrame {
    private JPanel mainPanel;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField idText;
    private JTextField emailText;
    private JPasswordField passwordText;
    private JButton signUpButton;
    private JButton backButton;
   

    public SignUpUI() {

        setTitle("Sign Up");
        setBounds(300, 90, 1000, 600);
        setResizable(false);
        setLayout(new BorderLayout());

        // Create a panel to hold the image
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        add(imgPanel, BorderLayout.WEST);

        // Load and display the image
        ImageIcon imageIcon = new ImageIcon("Images/SignUp.png");
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

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        firstNameText = new JTextField(20);
        firstNameText.setBackground(new Color(240, 240, 240));
        firstNameText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(firstNameText, gbc);;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        lastNameText = new JTextField(20);
        lastNameText.setBackground(new Color(240, 240, 240));
        lastNameText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(lastNameText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        idText = new JTextField(20);
        idText.setBackground(new Color(240, 240, 240));
        idText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(idText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailText = new JTextField(20);
        emailText.setBackground(new Color(240, 240, 240));
        emailText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(emailText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordText = new JPasswordField(20);
        passwordText.setBackground(new Color(240, 240, 240));
        passwordText.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(51, 153, 102));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(signUpButton, gbc);

        // Adjust the grid y-coordinate for the backButton to position it below the signUpButton
        gbc.gridy = 6;
        backButton = new JButton("Back");
        backButton.setBackground(new Color(51, 153, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(300, 50));
        mainPanel.add(backButton, gbc);


        signUpButton.addActionListener(new SignUpListener());
        backButton.addActionListener(new BackListener());

        setVisible(true);
    }

    private class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
            new WelcomeUI().setVisible(true);
        }
    }
    private class SignUpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String id = idText.getText();
            String email = emailText.getText();
            String password = new String(passwordText.getPassword());

            SignUpController signupcontrol = new SignUpController(firstName, lastName, id, email, password);

            boolean success = signupcontrol.signUpUser();
            if (success) {
                 // Close the sign-up window after successful sign-up
                JOptionPane.showMessageDialog(null, "Hurray! You may now proceed to the main menue");
                dispose();
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add user. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignUpUI().setVisible(true);
        });
    }
}