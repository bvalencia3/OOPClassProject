import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BankManager {

    /**
     * Handles Bank Manager operations, including creating new users, viewing
     * transactions, and generating bank statements.
     *
     * @param scanner the scanner to receive user input
     * @param records the list of user records
     * @param header  the header map for CSV records
     */
    public void handleBankManager(Scanner scanner, List<String[]> records, Map<String, Integer> header) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome, Bank Manager. What would you like to do?");
            System.out.println("1. View All Transactions");
            System.out.println("2. Create a New User");
            System.out.println("3. Generate a Bank Statement");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    viewAllTransactions();
                    break;
                case 2:
                    UserRegistration.registerNewUser(records);
                    break;
                case 3:
                    generateBankStatement(scanner, records, header);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Goodbye, Manager!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays all logged transactions in the system.
     */
    private void viewAllTransactions() {
        TransactionLogger logger = TransactionLogger.getInstance();
        List<String[]> allTransactions = logger.getAllTransactions();
        System.out.println("\n--- All Transactions ---");
        for (String[] transaction : allTransactions) {
            System.out.println(String.join(", ", transaction));
        }
        System.out.println("-------------------------");
    }

    /**
     * Generates a bank statement for a specific customer.
     *
     * @param scanner the scanner to receive user input
     * @param records the list of user records
     * @param header  the header map for CSV records
     */
    private void generateBankStatement(Scanner scanner, List<String[]> records, Map<String, Integer> header) {
        System.out.print("Enter the first name of the customer: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the customer: ");
        String lastName = scanner.nextLine();

        // Find the customer record
        String[] customerRecord = null;
        for (String[] record : records) {
            if (record[header.get("First Name")].equalsIgnoreCase(firstName)
                    && record[header.get("Last Name")].equalsIgnoreCase(lastName)) {
                customerRecord = record;
                break;
            }
        }

        if (customerRecord == null) {
            System.out.println("Customer not found.");
            return;
        }

        // Retrieve transactions for the customer
        TransactionLogger logger = TransactionLogger.getInstance();
        List<String[]> transactions = logger.getTransactionsForCustomer(firstName, lastName);

        // Generate the bank statement
        String fileName = firstName + "_" + lastName + "_BankStatement.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Bank Statement\n");
            writer.write("=======================\n");
            writer.write("Name: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + customerRecord[header.get("Address")] + "\n");
            writer.write("Phone Number: " + customerRecord[header.get("Phone Number")] + "\n");
            writer.write("\n--- Account Details ---\n");
            writer.write("Checking Balance: " + customerRecord[header.get("Checking Starting Balance")] + "\n");
            writer.write("Savings Balance: " + customerRecord[header.get("Savings Starting Balance")] + "\n");
            writer.write("Credit Balance: " + customerRecord[header.get("Credit Starting Balance")] + "\n");
            writer.write("Credit Limit: " + customerRecord[header.get("Credit Max")] + "\n");
            writer.write("\n--- Transactions ---\n");

            if (transactions.isEmpty()) {
                writer.write("No transactions available.\n");
            } else {
                for (String[] transaction : transactions) {
                    writer.write(String.join(", ", transaction) + "\n");
                }
            }

            System.out.println("Bank statement generated: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing bank statement: " + e.getMessage());
        }
    }
}
