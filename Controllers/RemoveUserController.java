package Controllers;

import java.io.*;
import java.util.ArrayList;

public class RemoveUserController {
    private static final String FILE_PATH = "TextFiles/userdata.txt";

    public boolean removeUser(String userId) {
        ArrayList<String> userData = readUserData();
        ArrayList<String> updatedData = new ArrayList<>();
        boolean found = false;

        for (String user : userData) {
            String[] userInfo = user.split(" ");
            if (containsUserId(userInfo, userId)) {
                found = true;
            } else {
                updatedData.add(user);
            }
        }

        if (!found) {
            return false; // User not found, return false
        }

        return writeUpdatedData(updatedData); // Write updated data to file
    }

    private boolean containsUserId(String[] userInfo, String userId) {
        for (String info : userInfo) {
            if (info.equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private boolean writeUpdatedData(ArrayList<String> updatedData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String data : updatedData) {
                writer.println(data);
            }
            return true; // Data written successfully
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error writing data
        }
    }

    private ArrayList<String> readUserData() {
        ArrayList<String> userData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }
}