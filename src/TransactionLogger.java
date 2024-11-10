import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles logging of transactions.
 * Writes transaction details to a log file with timestamp and transaction info.
 */
public class TransactionLogger {

    private static final String LOG_FILE = "transactions.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a transaction with a timestamp, transaction type, amount, balance, and
     * username
     *
     * @param userName        The name of the user performing the transaction
     * @param transactionType The type of transaction ("Deposit",
     *                        "Withdrawal", "Transfer")
     * @param amount          The transaction amount
     * @param newBalance      balance after the transaction
     * @param accountName     The account associated with the transaction
     */
    public static void logTransaction(String userName, String transactionType, double amount, double newBalance,
            String accountName) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] User: %s, %s of %.2f to %s. New Balance: %.2f",
                timestamp, userName, transactionType, amount, accountName, newBalance);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
