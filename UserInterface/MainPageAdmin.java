package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageAdmin extends JFrame {
    private JLabel header = new JLabel("UWI ON WHEELS");
    private JPanel mainScreenPanel = new JPanel();
    private JButton viewUsers = new JButton("VIEW USERS");
    private JButton deleteUsers = new JButton("DELETE USERS");
    private JButton logout = new JButton("LOGOUT");

    public MainPageAdmin() {
        setTitle("MainScreen");
        setBounds(300, 90, 420, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        header.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        header.setBounds(120, 20, 200, 30);
        mainScreenPanel.add(header);

        add(mainScreenPanel);
        mainScreenPanel.setLayout(null);
        mainScreenPanel.setBackground(Color.GREEN);

        viewUsers.setBounds(110, 300, 200, 35);
        deleteUsers.setBounds(110, 400, 200, 35);
        logout.setBounds(110, 500, 200, 35);

        mainScreenPanel.add(viewUsers);
        mainScreenPanel.add(deleteUsers);
        mainScreenPanel.add(logout);

        // Button listeners
        viewUsers.addActionListener(new ViewUserListener());
        logout.addActionListener(new LogoutAdminListener());
        deleteUsers.addActionListener(new DeleteListener());

        setVisible(true);
    }

    private class LogoutAdminListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            LoginUI login = new LoginUI();
        }
    }

    private class ViewUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                dispose();
                ViewUsers view = new ViewUsers();
            } catch (NullPointerException nulP) {
            }
        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Delete user logic
        }
    }
}