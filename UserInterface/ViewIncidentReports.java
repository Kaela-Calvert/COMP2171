package UserInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;

import Controllers.IncidentReportsController;

public class ViewIncidentReports extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton, returnButton;
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

        IncidentReportsController.loadIncidentReports(model);

        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Delete Report");
        returnButton = new JButton("Return");
        styleButton(deleteButton);
        styleButton(returnButton);

        buttonPanel.add(deleteButton);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(this::deleteReport);
        returnButton.addActionListener(this::returnToAdmin);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(51, 153, 102));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void deleteReport(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Remove from model
            model.removeRow(selectedRow);
            // Update the file
            IncidentReportsController.updateFile(model);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a report to delete.", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void returnToAdmin(ActionEvent e) {
        dispose();
        new MainPageAdmin().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewIncidentReports::new);
    }
}
