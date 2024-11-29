import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BankService {

    /**
     * Displays the balances of all accounts for the customer.
     *
     * @param customer the customer whose balances are to be displayed
     */
    public void checkBalance(Customer customer) {
        System.out.println("--- Account Balances ---");
        System.out.println("Checking: " + customer.getCheckingAccount().getBalance());
        System.out.println("Savings: " + customer.getSavingsAccount().getBalance());
        System.out.println("Credit: " + customer.getCreditAccount().getBalance());
        System.out.println("------------------------");
    }

    /**
     * Allows the customer to deposit into their accounts.
     *
     * @param customer the customer making the deposit
     * @param scanner  the scanner to receive user input
     * @param records  the list of user records
     */
    public void deposit(Customer customer, Scanner scanner, List<String[]> records) {
        System.out.println("Which account would you like to deposit into?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int accountChoice = scanner.nextInt();

        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        TransactionLogger logger = TransactionLogger.getInstance();
        if (accountChoice == 1) {
            customer.getCheckingAccount().deposit(amount);
            double newBalance = customer.getCheckingAccount().getBalance();
            logger.logTransaction(customer.getName(), "Deposit", amount, "Checking", newBalance);
        } else if (accountChoice == 2) {
            customer.getSavingsAccount().deposit(amount);
            double newBalance = customer.getSavingsAccount().getBalance();
            logger.logTransaction(customer.getName(), "Deposit", amount, "Savings", newBalance);
        } else {
            System.out.println("Invalid account choice.");
        }
    }

    /**
     * Allows the customer to withdraw funds from their accounts.
     *
     * @param customer the customer making the withdrawal
     * @param scanner  the scanner to receive user input
     * @param records  the list of user records
     */
    public void withdrawal(Customer customer, Scanner scanner, List<String[]> records) {
        System.out.println("Which account would you like to withdraw from?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int accountChoice = scanner.nextInt();

        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        TransactionLogger logger = TransactionLogger.getInstance();
        if (accountChoice == 1) {
            if (customer.getCheckingAccount().withdraw(amount)) {
                double newBalance = customer.getCheckingAccount().getBalance();
                logger.logTransaction(customer.getName(), "Withdrawal", amount, "Checking", newBalance);
            } else {
                System.out.println("Insufficient funds!");
            }
        } else if (accountChoice == 2) {
            if (customer.getSavingsAccount().withdraw(amount)) {
                double newBalance = customer.getSavingsAccount().getBalance();
                logger.logTransaction(customer.getName(), "Withdrawal", amount, "Savings", newBalance);
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Invalid account choice.");
        }
    }

    /**
     * Allows the customer to transfer funds between their checking and savings
     * accounts.
     *
     * @param customer the customer making the transfer
     * @param scanner  the scanner to receive user input
     * @param records  the list of user records
     */
    public void transfer(Customer customer, Scanner scanner, List<String[]> records) {
        System.out.println("Choose the direction of transfer:");
        System.out.println("1. Checking to Savings");
        System.out.println("2. Savings to Checking");
        int choice = scanner.nextInt();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        TransactionLogger logger = TransactionLogger.getInstance();
        boolean success = false;

        if (choice == 1) {
            if (customer.getCheckingAccount().withdraw(amount)) {
                customer.getSavingsAccount().deposit(amount);
                success = true;
                logger.logTransaction(customer.getName(), "Transfer", amount, "Checking to Savings",
                        customer.getCheckingAccount().getBalance());
            } else {
                System.out.println("Insufficient funds in Checking account.");
            }
        } else if (choice == 2) {
            if (customer.getSavingsAccount().withdraw(amount)) {
                customer.getCheckingAccount().deposit(amount);
                success = true;
                logger.logTransaction(customer.getName(), "Transfer", amount, "Savings to Checking",
                        customer.getSavingsAccount().getBalance());
            } else {
                System.out.println("Insufficient funds in Savings account.");
            }
        } else {
            System.out.println("Invalid transfer choice.");
        }

        if (success) {
            System.out.println("Transfer successful.");
        }
    }

    /**
     * Allows the customer to pay someone else by transferring funds.
     *
     * @param sender  the customer making the payment
     * @param scanner the scanner to receive user input
     * @param records the list of user records
     * @param header  the header map for the user records
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
            System.out.println("Payment of " + amount + " to " + recipient.getName() + " successful.");

            TransactionLogger logger = TransactionLogger.getInstance();
            logger.logTransaction(sender.getName(), "Payment Sent", amount, senderAccountType,
                    sender.getCheckingAccount().getBalance());
            logger.logTransaction(recipient.getName(), "Payment Received", amount, "Checking",
                    recipient.getCheckingAccount().getBalance());
        } else {
            System.out.println("Transaction failed due to insufficient funds or invalid account choice.");
        }
    }

}
