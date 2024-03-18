package Controllers;

import Source.LinkBike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeController {
    private static int linkedBikeID = -1;

    public static int getLinkedBikeID() {
        return linkedBikeID;
    }

    public static boolean linkToBike(int bikeID, String station) {
        if (!isUserLinked() && isValidBikeID(bikeID) && checkAvailability(bikeID).equals("Available")) {
            linkedBikeID = bikeID;
            LinkBike.updateBikeAvailability(bikeID, "NotAvailable");
            return true;
        } else {
            return false;
        }
    }

    public static boolean unlinkFromBike() {
        if (isUserLinked()) {
            LinkBike.updateBikeAvailability(linkedBikeID, "Available");
            linkedBikeID = -1;
            return true;
        } else {
            return false;
        }
    }

    public static boolean isUserLinked() {
        return linkedBikeID != -1;
    }

    public static boolean isValidBikeID(int bikeID) {
        return LinkBike.isValidBikeID(bikeID);
    }

    public static String checkAvailability(int bikeID) {
        return LinkBike.checkAvailability(bikeID);
    }

    public static List<BikeData> getBikesForStation(String stationName) {
        List<BikeData> bikes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("TextFiles/bikes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] bikeInfo = scanner.nextLine().split("_");
                if (bikeInfo[1].equalsIgnoreCase(stationName)) {
                    bikes.add(new BikeData(bikeInfo[0], bikeInfo[1], bikeInfo[2], bikeInfo[3], bikeInfo[4], bikeInfo[5], bikeInfo[6]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bikes;
    }

    public static class BikeData {
        private String bikeID;
        private String location;
        private String userRating;
        private String condition;
        private String availability;
        private String price;
        private String lateFee;

        public BikeData(String bikeID, String location, String userRating, String condition, String availability, String price, String lateFee) {
            this.bikeID = bikeID;
            this.location = location;
            this.userRating = userRating;
            this.condition = condition;
            this.availability = availability;
            this.price = price;
            this.lateFee = lateFee;
        }

        public String getBikeID() {
            return bikeID;
        }

        public String getLocation() {
            return location;
        }

        public String getUserRating() {
            return userRating;
        }

        public String getCondition() {
            return condition;
        }

        public String getAvailability() {
            return availability;
        }

        public String getPrice() {
            return price;
        }

        public String getLateFee() {
            return lateFee;
        }
    }
}
