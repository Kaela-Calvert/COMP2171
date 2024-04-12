package Controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ViewUsersController {

    private static final String USER_DATA_FILE = "TextFiles/userdata.txt";

    public List<List<String>> loadUserData() {
        List<List<String>> userData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                List<String> row = new ArrayList<>();
                for (String value : data) {
                    row.add(value);
                }
                userData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userData;
    }

    public void deleteUser(int rowIndex) {
        List<List<String>> userData = loadUserData();

        if (rowIndex >= 0 && rowIndex < userData.size()) {
            userData.remove(rowIndex);
            saveUserData(userData);
        } else {
            System.out.println("Invalid row index: " + rowIndex);
        }
    }

    private void saveUserData(List<List<String>> userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (List<String> row : userData) {
                StringBuilder rowString = new StringBuilder();
                for (String value : row) {
                    rowString.append(value).append(" ");
                }
                writer.write(rowString.toString().trim());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}