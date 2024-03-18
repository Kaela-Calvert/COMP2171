package Source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LinkBike {
    private static int linkedBikeID = -1; // Initialize as -1 to indicate no linked bike initially

    public static boolean linkToBike(int bikeID, String station) {
        // Check if there's already a linked bike
        if (linkedBikeID != -1) {
            JOptionPane.showMessageDialog(null, "You are already linked to Bike ID: " + linkedBikeID +
                    ". Please unlink before linking to a new bike.");
            return false;
        }

        // Add logic to verify bike ID (e.g., check if it exists in the system and is available)
        if (isValidBikeID(bikeID) && checkAvailability(bikeID).equals("Available")) {
            linkedBikeID = bikeID;
            updateBikeAvailability(bikeID, "NotAvailable"); // Set bike availability to false
            JOptionPane.showMessageDialog(null, "Successfully linked to Bike ID: " + linkedBikeID);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Bike ID or bike is not available: " + bikeID);
            return false;
        }
    }

    public static boolean unlinkFromBike() {
        if (linkedBikeID == -1) {
            JOptionPane.showMessageDialog(null, "You are not currently linked to any bike.");
            return false;
        } else {
            updateBikeAvailability(linkedBikeID, "Available"); // Set bike availability to true
            JOptionPane.showMessageDialog(null, "Successfully unlinked from Bike ID: " + linkedBikeID);
            linkedBikeID = -1; // Reset linkedBikeID
            return true;
        }
    }

    public static boolean isValidBikeID(int bikeID) {
        try (Scanner scanner = new Scanner(new File("TextFiles/bikes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] bikeInfo = scanner.nextLine().split("_");
                int currentID = Integer.parseInt(bikeInfo[0]);
                if (currentID == bikeID) {
                    return true; // Bike ID found
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false; // Bike ID not found
    }

    public static String checkAvailability(int bikeID) {
        try (Scanner scanner = new Scanner(new File("TextFiles/bikes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] bikeInfo = scanner.nextLine().split("_");
                int currentID = Integer.parseInt(bikeInfo[0]);
                if (currentID == bikeID) {
                    return bikeInfo[4]; // Return availability status
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null; // Bike ID not found or file not found
    }

    public static void updateBikeAvailability(int bikeID, String isAvailable) {
        // Update the availability of the bike in bikes.txt file
        try {
            File bikesFile = new File("TextFiles/bikes.txt");
            Scanner scanner = new Scanner(bikesFile);
            StringBuilder updatedContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] bikeInfo = line.split("_");
                int currentID = Integer.parseInt(bikeInfo[0]);
                if (currentID == bikeID) {
                    updatedContent.append(bikeID).append("_").append(bikeInfo[1]).append("_").append(bikeInfo[2]).append("_")
                            .append(bikeInfo[3]).append("_").append(isAvailable).append("_").append(bikeInfo[5]).append("_")
                            .append(bikeInfo[6]).append("\n");
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
            scanner.close();

            FileWriter writer = new FileWriter(bikesFile);
            writer.write(updatedContent.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateAndLinkBike(int bikeID, String station) {
        // Check if there's already a linked bike
        if (linkedBikeID != -1) {
            JOptionPane.showMessageDialog(null, "You are already linked to Bike ID: " + linkedBikeID +
                    ". Please unlink before linking to a new bike.");
            return false;
        }

        // Add logic to verify bike ID (e.g., check if it exists in the system and is available)
        if (isValidBikeID(bikeID) && checkAvailability(bikeID).equals("Available")) {
            linkedBikeID = bikeID;
            updateBikeAvailability(bikeID, "NotAvailable"); // Set bike availability to false
            JOptionPane.showMessageDialog(null, "Successfully linked to Bike ID: " + linkedBikeID);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Bike ID or bike is not available: " + bikeID);
            return false;
        }
    }
}
