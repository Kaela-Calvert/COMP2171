package Controllers;

import Source.Bike;

import java.io.*;
import java.util.*;

public class RateBikeController {
    private String ratesFilePath = "TextFiles/rates.txt";
    private String bikesFilePath = "TextFiles/bikes.txt";

    public boolean rateBike(String bikeID, String rating, String desc) {
        try {
            // Check if bikeID is valid
            if (!bikeExistsInFile(bikeID)) {
                System.out.println("Invalid bike ID.");
                return false;
            }

            // Add rating to rates.txt file
            addRatingToRatesFile(bikeID, rating, desc);

            // Update bike's current rate in bikes.txt file
            updateBikeRate(bikeID, Double.parseDouble(rating));

            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private boolean bikeExistsInFile(String bikeID) throws IOException {
        try (Scanner scanner = new Scanner(new File(bikesFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("_");

                if (parts.length >= 1 && parts[0].equals(bikeID)) {
                    return true;
                }
            }
        }
        return false; // Bike not found
    }

    private void addRatingToRatesFile(String bikeID, String rating, String desc) throws IOException {
        double currentRate = getBikeRate(bikeID);
        double newRating = Double.parseDouble(rating);
    
        double updatedRating = Math.round(((currentRate + newRating) / 2.0) * 10.0) / 10.0;
    
        try (FileWriter writer = new FileWriter(ratesFilePath, true)) {
            writer.write(bikeID + "_" + updatedRating + "_" + desc + "\n");
        }
    
        updateBikeRate(bikeID, updatedRating);
    }
   

    private void updateBikeRate(String bikeID, double newRating) throws IOException {
        double currentRate = getBikeRate(bikeID);
        int numOfRatings = getNumberOfRatings(bikeID);

        // Calculate new rate
        double newRate = calculateNewRate(currentRate, numOfRatings, newRating);

        // Update bike's rate in bikes.txt file
        updateBikeRateInFile(bikeID, newRate);
    }

    private double getBikeRate(String bikeID) throws IOException {
        double currentRate = 0.0;
        try (Scanner scanner = new Scanner(new File(ratesFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("_");

                if (parts.length > 0 && parts[0].equals(bikeID)) {
                    currentRate += Double.parseDouble(parts[1]);
                }
            }
        }
        return currentRate;
    }

    private int getNumberOfRatings(String bikeID) throws IOException {
        int numOfRatings = 0;
        try (Scanner scanner = new Scanner(new File(ratesFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("_");

                if (parts.length > 0 && parts[0].equals(bikeID)) {
                    numOfRatings++;
                }
            }
        }
        return numOfRatings;
    }
    
    private double calculateNewRate(double currentRate, int numOfRatings, double newRating) {
        return  Math.round(((newRating) / 2.0) * 10.0) / 10.0;
    }
    
    private void updateBikeRateInFile(String bikeID, double newRate) throws IOException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(bikesFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("_");
                if (parts.length >= 3 && parts[0].equals(bikeID)) {
                    parts[2] = String.valueOf(newRate);
                    line = String.join("_", parts);
                }
                lines.add(line);
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(bikesFilePath))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
}

