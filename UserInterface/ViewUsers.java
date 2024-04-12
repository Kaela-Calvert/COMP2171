package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Controllers.ViewUsersController;

public class ViewUsers extends JFrame {
    private JTable usersTable;
    private JScrollPane scrollPane;
    private JButton logoutButton = new JButton("LOGOUT");
    private JButton returnMainButton = new JButton("RETURN");
    private JButton deleteButton = new JButton("DELETE");
    private JPanel buttonPanel = new JPanel();
    private ViewUsersController viewUsersController;

    public ViewUsers() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("View Users");
        setBounds(300, 90, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        viewUsersController = new ViewUsersController();
        viewData();

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(logoutButton);
        buttonPanel.add(returnMainButton);
        buttonPanel.add(deleteButton);

        logoutButton.addActionListener(new LogoutListener());
        returnMainButton.addActionListener(new ReturnListener());
        deleteButton.addActionListener(new DeleteListener());

        logoutButton.setBackground(new Color(51, 153, 102));
        logoutButton.setForeground(Color.WHITE);
        returnMainButton.setBackground(new Color(51, 153, 102));
        returnMainButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(51, 153, 102));
        deleteButton.setForeground(Color.WHITE);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void viewData() {
        String[] columnNames = {"First Name", "Last Name", "ID", "Email"};
        List<List<String>> rowData = viewUsersController.loadUserData();

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        usersTable = new JTable(model);
        usersTable.getTableHeader().setDefaultRenderer(new HeaderRenderer(usersTable));

        for (List<String> row : rowData) {
            model.addRow(row.subList(0, 4).toArray());
        }

        scrollPane = new JScrollPane(usersTable);
        usersTable.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    private class HeaderRenderer implements TableCellRenderer {
        private final TableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setForeground(new Color(0, 0, 128));
            label.setBackground(new Color(51, 153, 102));
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            return label;
        }
    }

    private class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            LoginUI login = new LoginUI();
            login.setVisible(true);
        }
    }

    private class ReturnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainPageAdmin main = new MainPageAdmin();
            main.setVisible(true);
        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = usersTable.getSelectedRow();
            if (selectedRow != -1) {
                viewUsersController.deleteUser(selectedRow);
                ((DefaultTableModel) usersTable.getModel()).removeRow(selectedRow);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewUsers().setVisible(true));
    }
}