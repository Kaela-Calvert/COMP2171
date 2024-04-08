package Controllers;

import Source.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportController {
    public static void addReportToFile(String personName, String personID, String personContact,
                                       String description, String location, String bikeID,
                                       String witnessName, String witnessContact,
                                       String dateTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TextFiles/incidents.txt", true))) {
            Report report = new Report(personName, personID, personContact, description, location, bikeID, witnessName, witnessContact, dateTime);
            String reportString = report.toString();
            writer.write(reportString);
            writer.newLine();
            System.out.println("Report added to file: " + "TextFiles/incidents.txt");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }
}
