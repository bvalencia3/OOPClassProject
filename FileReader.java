
import java.io.*;
import java.util.*;



public class FileReader {
    public static List<String[]> readFile(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
