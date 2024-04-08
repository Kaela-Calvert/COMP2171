package Source;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bike {
    private String bikeId;
    private String bikeType;
    private double currentRate;
    private boolean isAvailable;

    public Bike(String bikeId, String bikeType, double current, boolean isAvailable) {
        this.bikeId = bikeId;
        this.bikeType = bikeType;
        this.currentRate = current;
        this.isAvailable = isAvailable;
    }

    // Getters and setters
    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Method to update bike rate and availability
    public void updateRateAndAvailability(double newRate) {
        // Update rate
        double updatedRate = (currentRate + newRate) / 2;
        setCurrentRate(updatedRate);

        // Update availability
        setAvailable(true); // Assuming the bike becomes available after rating
    }

    // Method to save updated bike information to file
    public void saveToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(getBikeId() + "_" + getBikeType() + "_" + getCurrentRate() + "_Available_10.00_5.00\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to retrieve all bikes from file
    public static List<Bike> getAllBikes(String fileName) {
        List<Bike> bikes = new ArrayList<>();
        // Read bikes from file and populate the list
        // Example code: read file, split lines, create Bike objects, add to list
        return bikes;
    }

    // Other methods as needed
}
