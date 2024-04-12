package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.table.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class ViewUsers extends JFrame {
    private JTable usersTable;
    private JScrollPane scrollPane;
    private JButton logoutButton = new JButton("LOGOUT");
    private JButton returnMainButton = new JButton("RETURN");
    private JButton deleteButton = new JButton("DELETE");
    private JPanel buttonPanel = new JPanel();

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

        viewData(); // Setup JTable with data

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
        String[] columnNames = {"First Name", "Last Name", "ID", "Email", "Password"};
        Vector<Vector<String>> rowData = new Vector<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/joeldixon/Downloads/COMP2171-main-2/TextFiles/userdata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                Vector<String> row = new Vector<>();
                for (String value : data) {
                    row.add(value);
                }
                rowData.add(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load data due to an unexpected error:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        DefaultTableModel model = new DefaultTableModel(rowData, new Vector<>(java.util.Arrays.asList(columnNames)));
        usersTable = new JTable(model);
        usersTable.getTableHeader().setDefaultRenderer(new HeaderRenderer(usersTable));
        scrollPane = new JScrollPane(usersTable);
        usersTable.setFillsViewportHeight(true);
    
        add(scrollPane, BorderLayout.CENTER);
    }

    // Define HeaderRenderer as an inner class or standalone class if you prefer
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
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center text
        label.setForeground(new Color(0, 0, 128)); // Navy blue color
        label.setBackground(new Color(51, 153, 102)); // Dark green background
        label.setFont(label.getFont().deriveFont(Font.BOLD)); // Set font to bold
        return label;
        }
    }

    private class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            LoginUI login = new LoginUI(); // Assume this opens the login window
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

    private void deleteUserFromFile(int rowIndex) {
        File inputFile = new File("/Users/joeldixon/Downloads/COMP2171-main-2/TextFiles/userdata.txt");
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentRowIndex = 0;
            while ((currentLine = reader.readLine()) != null) {
                if (currentRowIndex != rowIndex) {
                    writer.write(currentLine + System.lineSeparator());
                }
                currentRowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not delete user");
            // Handle failure to rename file
        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = usersTable.getSelectedRow();
            if (selectedRow != -1) {
                deleteUserFromFile(selectedRow);
                ((DefaultTableModel) usersTable.getModel()).removeRow(selectedRow);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewUsers().setVisible(true));
    }
}
