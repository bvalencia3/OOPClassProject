import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * 
 *         The FileReaderUtility class provides a method to read CSV files using
 *         OpenCSV.
 */
public class FileReaderUtility {

    private Map<String, Integer> headerMap;

    public List<String[]> readFile(String filePath) {
        List<String[]> records = new ArrayList<>();
        this.headerMap = new HashMap<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] row;
            int rowIndex = 0;

            while ((row = csvReader.readNext()) != null) {
                if (rowIndex == 0) { // Treat the first row as the header
                    for (int i = 0; i < row.length; i++) {
                        headerMap.put(row[i].trim(), i);
                    }
                } else {
                    records.add(row);
                }
                rowIndex++;
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return records;
    }

    /**
     * Returns a Map for the header row of the CSV file, mapping column names to
     * their indices.
     */
    public Map<String, Integer> getHeader() {
        return this.headerMap;
    }
}
