package Source;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LinkBike {
    private static int linkedBikeID = -1; // Initialize as -1 to indicate no linked bike initially
    private static User currentUser; // Add a reference to the current user

    public static boolean linkToBike(int bikeID, String station, User user) {
        // Check if the user has a payment plan
        if (!user.hasPaymentPlan()) {
            JOptionPane.showMessageDialog(null, "You must have a payment plan before linking a bike.");
            return false;
        }

        // Check if the user is already linked to a bike
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
        try (BufferedReader br = new BufferedReader(new FileReader("TextFiles/bikes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bikeInfo = line.split("_");
                int currentID = Integer.parseInt(bikeInfo[0]);
                if (currentID == bikeID) {
                    return true; // Bike ID found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle file reading or parsing errors
        }
        return false; // Bike ID not found
    }

    public static String checkAvailability(int bikeID) {
        try (BufferedReader br = new BufferedReader(new FileReader("TextFiles/bikes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bikeInfo = line.split("_");
                int currentID = Integer.parseInt(bikeInfo[0]);
                if (currentID == bikeID) {
                    return bikeInfo[4]; // Return availability status
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle file reading or parsing errors
        }
        return null; // Bike ID not found or file not found
    }

    public static void updateBikeAvailability(int bikeID, String isAvailable) {
        // Update the availability of the bike in bikes.txt file
        try (BufferedReader br = new BufferedReader(new FileReader("TextFiles/bikes.txt"))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
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

            try (FileWriter writer = new FileWriter("TextFiles/bikes.txt")) {
                writer.write(updatedContent.toString());
            } catch (IOException e) {
                e.printStackTrace(); // Handle file writing errors
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading errors
        }
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static int getLinkedBikeID() {
        int bikeID = 0; // Default value if no linked bike ID is found
        try (BufferedReader br = new BufferedReader(new FileReader("TextFiles/bikes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bikeData = line.split("_");
                if (bikeData.length >= 5 && bikeData[4].equalsIgnoreCase("NotAvailable")) {
                    bikeID = Integer.parseInt(bikeData[0]);
                    break; // Stop searching once a linked bike ID is found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle file reading or parsing errors
        }
        return bikeID;
    }
}
