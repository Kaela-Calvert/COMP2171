package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainPageAdmin extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JButton viewUsersButton;
    private JButton deleteUsersButton;
    private JButton logoutButton;
    private JPanel imgPanel;

    public MainPageAdmin() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        setTitle("Admin Main Menu");
        setBounds(300, 90, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLUE); // Set the same background color as buttonsPanel
        add(mainPanel, BorderLayout.CENTER);

        // Image panel setup
        imgPanel = new JPanel();
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("Images/Admin.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        Dimension size = new Dimension(400, 400);
        picLabel.setPreferredSize(size);
        imgPanel.add(picLabel);
        imgPanel.setBackground(new Color(51, 102, 153));
        mainPanel.add(imgPanel, BorderLayout.NORTH);

        // Buttons panel setup
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonsPanel.setBackground(new Color(51, 102, 153));

        viewUsersButton = new JButton("VIEW USERS");
        deleteUsersButton = new JButton("DELETE USERS");
        logoutButton = new JButton("LOGOUT");

        // Set the same background color as the login button (assuming it's a blue color)
        viewUsersButton.setBackground(new Color(51, 153, 102));
        viewUsersButton.setForeground(Color.WHITE);
        deleteUsersButton.setBackground(new Color(51, 153, 102));
        deleteUsersButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(51, 153, 102));
        logoutButton.setForeground(Color.WHITE);

        viewUsersButton.setPreferredSize(new Dimension(200, 45));
        deleteUsersButton.setPreferredSize(new Dimension(200, 45));
        logoutButton.setPreferredSize(new Dimension(200, 45));

        buttonsPanel.add(viewUsersButton);
        buttonsPanel.add(deleteUsersButton);
        buttonsPanel.add(logoutButton);

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Button listeners
        viewUsersButton.addActionListener(new ViewUserListener());
        deleteUsersButton.addActionListener(new DeleteListener());
        logoutButton.addActionListener(new LogoutAdminListener());

        setVisible(true);
    }

    private class ViewUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                dispose();
                ViewUsers view = new ViewUsers();
            } catch (NullPointerException nulP) {
                // Handle NullPointerException if needed
            }
        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Delete user logic
        }
    }

    private class LogoutAdminListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            LoginUI login = new LoginUI();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPageAdmin().setVisible(true);
        });
    }
}