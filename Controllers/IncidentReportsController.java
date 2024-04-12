package Controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Vector;

public class IncidentReportsController {

    public static void loadIncidentReports(DefaultTableModel model) {
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
            JOptionPane.showMessageDialog(null, "Error loading incident reports.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateFile(DefaultTableModel model) {
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
            JOptionPane.showMessageDialog(null, "Error updating the file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
