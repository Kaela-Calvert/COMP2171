package UserInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Vector;

public class ViewIncidentReports extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton, logoutButton, returnButton;
    private JScrollPane scrollPane;

    public ViewIncidentReports() {
        setTitle("Incident Reports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Person Name", "Person ID", "Person Contact", "Description", "Location", "Bike ID", "Witness Name", "Witness Contact", "Date and Time"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        // Set font for table content
        Font tableFont = new Font("SansSerif", Font.PLAIN, 18); // Increased font size for table content
        table.setFont(tableFont);
        table.setRowHeight(25);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Header style
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(51, 153, 102));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        loadIncidentReports();

        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Delete Report");
        logoutButton = new JButton("Logout");
        returnButton = new JButton("Return");
        styleButton(deleteButton);
        styleButton(logoutButton);
        styleButton(returnButton);

        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(this::deleteReport);
        logoutButton.addActionListener(this::logout);
        returnButton.addActionListener(this::returnToAdmin);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(51, 153, 102));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void loadIncidentReports() {
        File file = new File("TextFiles/incidents.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Incident Report:")) {
                    Vector<String> incident = new Vector<>();
                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        incident.add(line.substring(line.indexOf(':') + 2));
                    }
                    model.addRow(incident);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading incident reports.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteReport(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Remove from model
            model.removeRow(selectedRow);
            // Update the file
            updateFile();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a report to delete.", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void updateFile() {
        File file = new File("TextFiles/incidents.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                writer.write("Incident Report:");
                writer.newLine();
                writer.write("Person Name: " + model.getValueAt(row, 0));
                writer.newLine();
                writer.write("Person ID: " + model.getValueAt(row, 1));
                writer.newLine();
                writer.write("Person Contact: " + model.getValueAt(row, 2));
                writer.newLine();
                writer.write("Description: " + model.getValueAt(row, 3));
                writer.newLine();
                writer.write("Location: " + model.getValueAt(row, 4));
                writer.newLine();
                writer.write("Bike ID: " + model.getValueAt(row, 5));
                writer.newLine();
                writer.write("Witness Name: " + model.getValueAt(row, 6));
                writer.newLine();
                writer.write("Witness Contact: " + model.getValueAt(row, 7));
                writer.newLine();
                writer.write("Date and Time: " + model.getValueAt(row, 8));
                writer.newLine();
                writer.newLine(); // Add a blank line to separate incidents
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating the file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void logout(ActionEvent e) {
        dispose();
        new LoginUI().setVisible(true);
    }

    private void returnToAdmin(ActionEvent e) {
        dispose();
        new MainPageAdmin().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewIncidentReports::new);
    }
}
