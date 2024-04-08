package UserInterface;

import Controllers.SignUpController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class WelcomeUI extends JFrame {
    private JPanel mainPanel;
    private JPanel imgPanel;
    private JButton loginButton;
    private JButton signUpButton;

    public WelcomeUI() {
        // Set the title and properties of the JFrame
        setTitle("UWI ON WHEELS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null);

        // Set the background color of the frame
        getContentPane().setBackground(new Color(240, 240, 240));

        // Create the main panel with BorderLayout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Create a panel for buttons with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(new Color(51, 153, 255));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Create the "Login" button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(51, 153, 102));
        loginButton.setPreferredSize(new Dimension(300, 50));
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.addMouseListener(new HoverListener(loginButton));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open login screen
                dispose();
                LoginUI loginUI = new LoginUI();
                loginUI.setVisible(true);
            }
        });
        buttonPanel.add(loginButton);

        // Create the "Sign Up" button
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(51, 153, 102));
        signUpButton.setPreferredSize(new Dimension(300, 50));
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        signUpButton.addMouseListener(new HoverListener(signUpButton));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open sign-up screen
                dispose();
                SignUpUI signUpUI = new SignUpUI();
                signUpUI.setVisible(true);
            }
        });
        buttonPanel.add(signUpButton);

        // Create a panel for the image and welcome message
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(51, 102, 153));
        mainPanel.add(topPanel, BorderLayout.CENTER);

        // Add the welcome message
        JLabel welcomeLabel = new JLabel("Welcome to UWI ON WHEELS", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create a panel for the image
        imgPanel = new JPanel();
        imgPanel.setBackground(new Color(51, 102, 153));
        topPanel.add(imgPanel, BorderLayout.CENTER);

        try {
            // Load the image and add it to the imgPanel
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/UOW.png"));
            JLabel imageLabel = new JLabel(imageIcon);
            imgPanel.add(imageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class HoverListener extends MouseAdapter {
        private final JButton button;
        private final Color defaultColor;

        public HoverListener(JButton button) {
            this.button = button;
            this.defaultColor = button.getBackground();
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            button.setBackground(defaultColor.brighter());
            button.setBorder(new RoundedBorder(10));
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            button.setBackground(defaultColor);
            button.setBorder(new EmptyBorder(0, 0, 0, 0));
        }
    }

    private class RoundedBorder extends EmptyBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            super(0, 0, 0, 0);
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(c.getBackground());
            g2d.fillRoundRect(x, y, width, height, radius, radius);
            g2d.setColor(Color.WHITE);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomeUI().setVisible(true);
        });
    }
}
