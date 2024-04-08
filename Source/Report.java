package Source;

public class Report {
    private String personName;
    private String personID;
    private String personContact;
    private String description;
    private String location;
    private String bikeID;
    private String witnessName;
    private String witnessContact;
    private String dateTime;

    public Report(String personName, String personID, String personContact,
                  String description, String location, String bikeID,
                  String witnessName, String witnessContact,
                  String dateTime) {
        this.personName = personName;
        this.personID = personID;
        this.personContact = personContact;
        this.description = description;
        this.location = location;
        this.bikeID = bikeID;
        this.witnessName = witnessName;
        this.witnessContact = witnessContact;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Incident Report:\n" +
                "Person Name: " + personName + "\n" +
                "Person ID: " + personID + "\n" +
                "Person Contact: " + personContact + "\n" +
                "Description: " + description + "\n" +
                "Location: " + location + "\n" +
                "Bike ID: " + bikeID + "\n" +
                "Witness Name: " + witnessName + "\n" +
                "Witness Contact: " + witnessContact + "\n" +
                "Date and Time: " + dateTime + "\n";
    }
}
