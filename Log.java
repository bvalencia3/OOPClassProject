
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The Log class provides functionality to log transactions to a file.
 */
public class Log {

    private static final String LOG_FILE = "transactions.log";

     /**
     * Logs a transaction with a timestamp to the log file.
     * 
     * @param message the transaction message to log
     */
    public static void logTransaction(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing log file: " + e.getMessage());
        }
    }
}
