package main.tile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

    public static int mapWidth = 10;
    public static int mapHeight = 10;

    public static String[][] read2DArrayFromFile(String filePath) throws IOException {
        List<String[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line based on a delimiter (e.g., comma, space)
                String[] rowData = line.split(","); // Adjust delimiter as needed
                dataList.add(rowData);
            }
        }
        // Convert the ArrayList of String arrays to a 2D String array
        return dataList.toArray(new String[0][]);
    }

    public static String[][] gameMap(String filePath) {

        List<String[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line based on a delimiter (e.g., comma, space)
                String[] rowData = line.split(" "); // Adjust delimiter as needed
                dataList.add(rowData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Convert the ArrayList of String arrays to a 2D String array
        return dataList.toArray(new String[dataList.size()][]);
    }
}
