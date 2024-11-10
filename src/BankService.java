import java.util.List;
import java.util.Scanner;
import java.util.Map;

/**
 * BankService class will handle all the bank option methods
 * To include, checkbalance, withdraw, transfer, and deposit methods
 */
public class BankService {

    /**
     * Displays the balance of the customer's accounts.
     * 
     * @param customer the customer whose balances are to be displayed
     */
    public void checkBalance(Customer customer) {
        System.out.println();
        System.out.println("--- Account Balances ---");
        System.out.println("Checking: " + customer.getCheckingAccount().getBalance());
        System.out.println("Savings: " + customer.getSavingsAccount().getBalance());
        System.out.println("Credit: " + customer.getCreditAccount().getBalance());
        System.out.println();
        System.out.println("-----------------------");
    }

    /**
     * Deposits a specified amount into a selected account of the customer.
     * 
     * @param customer the customer making the deposit
     * @param scanner  a scanner to receive user input
     * @param records  the list of user records to update with the new balance
     */
    public void deposit(Customer customer, Scanner scanner, List<String[]> records) {
        System.out.println("Which account would you like to deposit into?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int accountChoice = scanner.nextInt();

        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        String userName = customer.getName();
        if (accountChoice == 1) {
            customer.getCheckingAccount().deposit(amount);
            double newBalance = customer.getCheckingAccount().getBalance();
            System.out.println("Deposited " + amount + " to Checking account.");
            Log.logTransaction(userName, "Deposit", amount, newBalance, "Checking");
        } else if (accountChoice == 2) {
            customer.getSavingsAccount().deposit(amount);
            double newBalance = customer.getSavingsAccount().getBalance();
            System.out.println("Deposited " + amount + " to Savings account.");
            Log.logTransaction(userName, "Deposit", amount, newBalance, "Savings");
        } else {
            System.out.println("Invalid account choice.");
        }
        updateCsvRecords(records, customer); // updates csv file
    }

    /**
     * Withdraws a specified amount from a selected account of the customer.
     * 
     * @param customer the customer making the withdrawal
     * @param scanner  a scanner to receive user input
     * @param records  the list of user records to update with the new balance
     */
    public void withdrawal(Customer customer, Scanner scanner, List<String[]> records) {
        System.out.println("Which account would you like to withdraw from?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int accountChoice = scanner.nextInt();

        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        String userName = customer.getName();
        if (accountChoice == 1) {
            if (customer.getCheckingAccount().withdraw(amount)) {
                double newBalance = customer.getCheckingAccount().getBalance();
                System.out.println("Withdrew " + amount + " from Checking account.");
                Log.logTransaction(userName, "Withdrawal", amount, newBalance, "Checking");
            } else {
                System.out.println("Insufficient funds!");
            }
        } else if (accountChoice == 2) {
            if (customer.getSavingsAccount().withdraw(amount)) {
                double newBalance = customer.getSavingsAccount().getBalance();
                System.out.println("Withdrew " + amount + " from Savings account.");
                Log.logTransaction(userName, "Withdrawal", amount, newBalance, "Savings");
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Invalid account choice.");
        }
        updateCsvRecords(records, customer); // updates csv file
    }

    /**
     * Transfers a specified amount between the customer's checking and savings
     * accounts.
     * 
     * @param customer the customer making the transfer
     * @param scanner  a scanner to receive user input
     * @param records  the list of user records to update with the new balances
     */
    public void transfer(Customer customer, Scanner scanner, List<String[]> records) {
        System.out.println("Choose the direction of transfer:");
        System.out.println("1. Checking to Savings");
        System.out.println("2. Savings to Checking");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        String userName = customer.getName();
        boolean success = false;
        if (choice == 1) {
            // Transfer from Checking to Savings
            if (customer.getCheckingAccount().withdraw(amount)) {
                customer.getSavingsAccount().deposit(amount);
                double newBalanceChecking = customer.getCheckingAccount().getBalance();
                double newBalanceSavings = customer.getSavingsAccount().getBalance();
                System.out.println("Transferred " + amount + " from Checking to Savings.");
                Log.logTransaction(userName, "Transfer", amount, newBalanceChecking, "Checking");
                Log.logTransaction(userName, "Transfer", amount, newBalanceSavings, "Savings");
                success = true;
            } else {
                System.out.println("Insufficient funds!");
            }
        } else if (choice == 2) {
            // Transfer from Savings to Checking
            if (customer.getSavingsAccount().withdraw(amount)) {
                customer.getCheckingAccount().deposit(amount);
                double newBalanceSavings = customer.getSavingsAccount().getBalance();
                double newBalanceChecking = customer.getCheckingAccount().getBalance();
                System.out.println("Transferred " + amount + " from Savings to Checking.");
                Log.logTransaction(userName, "Transfer", amount, newBalanceSavings, "Savings");
                Log.logTransaction(userName, "Transfer", amount, newBalanceChecking, "Checking");
                success = true;
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        if (success) {
            updateCsvRecords(records, customer); // Update CSV records ONLY if the transfer was successful
        }
    }

    /**
     * Pays an amount from the customer to another user.
     * 
     * @param sender  the customer sending the payment
     * @param scanner a scanner to receive user input
     * @param records the list of user records to update with the new balances
     */
    public void paySomeone(Customer sender, Scanner scanner, List<String[]> records, Map<String, Integer> header) {
        System.out.print("Enter the first name of the person you want to pay: ");
        String recipientFirstName = scanner.next();
        System.out.print("Enter the last name of the person you want to pay: ");
        String recipientLastName = scanner.next();

        if (sender.getName().equalsIgnoreCase(recipientFirstName + " " + recipientLastName)) {
            System.out.println("You cannot pay yourself.");
            return;
        }

        Customer recipient = RunBank.findCustomer(records, header, recipientFirstName, recipientLastName);
        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        System.out.print("Enter the amount to pay: ");
        double amount = scanner.nextDouble();

        System.out.println("Select your account to pay from:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int fromAccountChoice = scanner.nextInt();

        System.out.println("Select recipient's account to pay into:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int toAccountChoice = scanner.nextInt();

        boolean transactionSuccess = false;
        // Determine account type for sending funds
        String senderAccountType;
        if (fromAccountChoice == 1) {
            senderAccountType = "Checking";
            System.out.println("Funds sent from - Checkings");
        } else if (fromAccountChoice == 2) {
            senderAccountType = "Savings";
            System.out.println("Funds sent from - Savings");
        } else {
            senderAccountType = "Unknown";
            System.out.println("Invalid choice");
        }

        // Determine the account type for receiving funds
        String recipientAccountType;
        if (toAccountChoice == 1) {
            recipientAccountType = "Checking";
            System.out.println("Deposited to recipient's Checkings");
        } else if (toAccountChoice == 2) {
            recipientAccountType = "Savings";
            System.out.println("Deposited to recipient's Savings");
        } else {
            recipientAccountType = "Unknown";
            System.out.println("Invalid choice");
        }

        // Log invalid account type selections as needed
        if ("Unknown".equals(senderAccountType) || "Unknown".equals(recipientAccountType)) {
            System.out.println("Transaction could not proceed due to invalid account type");
        }

        // From Checking
        if (fromAccountChoice == 1) {
            if (sender.getCheckingAccount().getBalance() >= amount) {
                sender.getCheckingAccount().withdraw(amount);
                if (toAccountChoice == 1) {
                    recipient.getCheckingAccount().deposit(amount);
                } else {
                    recipient.getSavingsAccount().deposit(amount);
                }
                transactionSuccess = true;
            }

            // From Savings
        } else if (fromAccountChoice == 2) {
            if (sender.getSavingsAccount().getBalance() >= amount) {
                sender.getSavingsAccount().withdraw(amount);
                if (toAccountChoice == 1) {
                    recipient.getCheckingAccount().deposit(amount);
                } else {
                    recipient.getSavingsAccount().deposit(amount);
                }
                transactionSuccess = true;
            }
        }

        if (transactionSuccess) {
            System.out.println("Paid " + amount + " to " + recipient.getName() + " successfully.");
            updateCsvRecords(records, sender); // Update sender's balance in CSV
            updateCsvRecords(records, recipient); // Update recipient's balance in CSV

            // Log the transaction for the sender, including the payee's name
            String senderLogMessage = "Payment Sent to " + recipient.getName();
            Log.logTransaction(sender.getName(), senderLogMessage, amount,
                    sender.getCheckingAccount().getBalance(), senderAccountType);

            // Log the transaction for the recipient, including the payer's name
            String recipientLogMessage = "Payment Received from " + sender.getName();
            Log.logTransaction(recipient.getName(), recipientLogMessage, amount,
                    recipient.getCheckingAccount().getBalance(), recipientAccountType);
        } else {
            System.out.println("Transaction failed due to insufficient funds or invalid account choice.");
        }
    }

    /**
     * Updates the customer's balance in the CSV records.
     * 
     * @param records  the list of user records
     * @param customer the customer whose balance is updated
     */
    public void updateCsvRecords(List<String[]> records, Customer customer) {
        String[] customerName = customer.getName().split(" ");
        String firstName = customerName[0];
        String lastName = customerName[1];

        for (int i = 0; i < records.size(); i++) {
            String[] record = records.get(i);
            if (record[1].trim().equalsIgnoreCase(firstName) && record[2].trim().equalsIgnoreCase(lastName)) {
                record[9] = String.valueOf(customer.getCheckingAccount().getBalance());
                record[11] = String.valueOf(customer.getSavingsAccount().getBalance());
                break;
            }
        }

        FileWriterUtility.writeToFile("Accounts.csv", records);
    }
}
