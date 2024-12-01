import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 *         File writer class that writes records to CSV
 */

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

    /**
     * Generates the User Transaction File
     * 
     * @param customer
     * @param checkStarting
     * @param savStarting
     * @param credStarting
     */
    public void generateTransFile(Customer customer, double checkStarting, double savStarting, double credStarting) {
        String filePath = customer.getName() + "_transactions.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            formHeader(writer, customer);
            formStartBalances(writer, checkStarting, savStarting, credStarting);
            formTransactions(writer, customer);
            formEndBalances(writer, customer);
        } catch (IOException e) {
            System.out.println("Error writing to txt file: " + e.getMessage());
        }
    }

    private void formHeader(BufferedWriter writer, Customer customer) {
        try {
            writer.write(customer.getName());
            writer.newLine();
            writer.write("ID: " + customer.getId());
            writer.newLine();
            writer.write(
                    "----------------------------------------------------------------------------------------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to txt file: " + e.getMessage());
        }
    }

    private void formStartBalances(BufferedWriter writer, double checkStarting, double savStarting,
            double credStarting) {
        try {
            writer.write("Checking Starting Balance: " + checkStarting + " | Savings Starting Balance: " + savStarting
                    + " | Credit Starting Balance: " + credStarting);
            writer.newLine();
            writer.write(
                    "----------------------------------------------------------------------------------------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to txt file: " + e.getMessage());

        }
    }

    private void formTransactions(BufferedWriter writer, Customer customer) {
        String name = customer.getName();
        String pattern = "User: " + name;

        try (BufferedReader logReader = new BufferedReader(new FileReader("transactions.log"))) {
            String line;
            while ((line = logReader.readLine()) != null) {
                if (line.contains(pattern)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }
        try {
            writer.write(
                    "----------------------------------------------------------------------------------------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to txt file: " + e.getMessage());
        }
    }

    private void formEndBalances(BufferedWriter writer, Customer customer) {
        try {
            writer.write("Account balances after transactions:");
            writer.newLine();
            writer.write("Checking Account Balance: " + customer.getCheckingAccount().getBalance());
            writer.newLine();
            writer.write("Savings Account Balance: " + customer.getSavingsAccount().getBalance());
            writer.newLine();
            writer.write("Credit Account Balance: " + customer.getCreditAccount().getBalance());
        } catch (IOException e) {
            System.out.println("Error writing to txt file: " + e.getMessage());
        }
    }
}
