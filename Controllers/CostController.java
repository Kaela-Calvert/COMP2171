package Controllers;

public class CostController {
    private static final double HOURLY_RATE = 1000; 

    public static double calculateCost(long rentalDurationHours) {
        return rentalDurationHours * HOURLY_RATE;
    }
}