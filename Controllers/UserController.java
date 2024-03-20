package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserController {
    private static final String USER_DATA_FILE = "TextFiles/userdata.txt";

    public String getUserName(String userId) {
        String userName = "";
        try (Scanner scanner = new Scanner(new File(USER_DATA_FILE))) {
            while (scanner.hasNextLine()) {
                String userData = scanner.nextLine();
                String[] userFields = userData.split(" ");
                if (userFields.length >= 5 && userFields[1].equals(userId)) {
                    userName = userFields[0] + " " + userFields[2]; // Concatenate first and last name
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return userName;
    }
}

