import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtility {

    /**
     * Writes updated records to the specified CSV file.
     *
     * @param filePath the path to the CSV file
     * @param records  the list of records to write to the file
     */
    public static void writeToFile(String filePath, List<String[]> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
