package UserInterface;

import javax.swing.*;

import Source.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewUsers extends JFrame {
    public JTextArea displayArea = new JTextArea();
    private JLabel header = new JLabel("UWI ON WHEELS");
    private JPanel displayPanel = new JPanel();
    private JScrollPane scrollPane;
    private JButton deleteButton = new JButton("DELETE");
    private JButton logout = new JButton("LOGOUT");
    private JButton returnMain = new JButton("RETURN");
    private JPanel buttonPanel = new JPanel();
    private static ArrayList<User> users = new ArrayList<>();

    public ViewUsers() {
        setBounds(300, 90, 420, 700);
        setResizable(false);

        header.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
        header.setBounds(120, 20, 200, 30);
        displayPanel.add(header);

        add(displayPanel);
        add(buttonPanel, BorderLayout.SOUTH);
        displayPanel.setBackground(Color.GREEN);
        displayArea = new JTextArea(34, 30);
        displayArea.setBorder(BorderFactory.createLineBorder(Color.black));

        displayPanel.add(displayArea);
        scrollPane = new JScrollPane(displayArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        displayPanel.add(scrollPane);

        logout.setBounds(10, 500, 80, 25);
        buttonPanel.add(logout);

        returnMain.setBounds(10, 600, 80, 25);
        buttonPanel.add(returnMain);

        displayArea.setEditable(false);

        viewData();
        setVisible(true);

        // Button listeners
        logout.addActionListener(new LogoutListener());
        returnMain.addActionListener(new ReturnListener());
        deleteButton.addActionListener(new DeleteListener());
    }

    private void viewData() {
        // Code to display user data in the text area
    }

    private class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            LoginUI login = new LoginUI();
        }
    }

    private class ReturnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainPageAdmin main = new MainPageAdmin();
        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Delete user logic
        }
    }
}