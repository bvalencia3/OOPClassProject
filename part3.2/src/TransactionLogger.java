import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * 
 *         Class that handles the records for transactions and logs them into a
 *         file
 */

public class TransactionLogger {

    private static TransactionLogger instance; // Single instance
    private static final String LOG_FILE = "transactions.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final List<String[]> transactions; // Transaction list (In memory)

    // Private constructor to prevent instantiation
    private TransactionLogger() {
        transactions = new ArrayList<>();
    }

    // Public method to get the singleton instance
    // Only one instance of TransactionLogger exists in the entire program
    // Singleton instance is created using a private constructor.
    public static synchronized TransactionLogger getInstance() {
        if (instance == null) {
            instance = new TransactionLogger();
        }
        return instance;
    }

    // Logs transaction
    public void logTransaction(String userName, String transactionType, double amount, String accountName,
            double newBalance) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] User: %s, %s of %.2f to %s. New Balance: %.2f",
                timestamp, userName, transactionType, amount, accountName, newBalance);

        // Add to in-memory transaction list
        transactions.add(new String[] { timestamp, userName, transactionType, String.valueOf(amount), accountName,
                String.valueOf(newBalance) });

        // Write to log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    // Retrieve all transactions
    public List<String[]> getAllTransactions() {
        // returns copy to prevent modification
        return new ArrayList<>(transactions);
    }

    // Retrieve transactions for a specific customer
    public List<String[]> getTransactionsForCustomer(String firstName, String lastName) {
        List<String[]> userTransactions = new ArrayList<>();
        String fullName = firstName + " " + lastName;

        for (String[] transaction : transactions) {
            if (transaction[1].equalsIgnoreCase(fullName)) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }
}
