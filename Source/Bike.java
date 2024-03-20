package Source;

public class Bike {
    private String bikeId;
    private String bikeType;
    private boolean isAvailable;

    public Bike(String bikeId, String bikeType, boolean isAvailable) {
        this.bikeId = bikeId;
        this.bikeType = bikeType;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
