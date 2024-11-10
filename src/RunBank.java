import java.util.List;
import java.util.Scanner;
import java.util.Map;

/**
 * Main class for the miners banking system.
 * Manages user interactions and allows different banking operations.
 * 
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 */
public class RunBank {

    private static BankService bankService = new BankService();

    /**
     * Starts the banking application and prompts the user for actions.
     * Initializes customer or manager options and processes user commands.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Miners Bank");
        System.out.println("Are you a:");
        System.out.println("1. Existing Customer");
        System.out.println("2. Bank Manager");
        System.out.println("3. New User Account Registration");
        System.out.println();
        System.out.print("Choose an option: ");
        int userType = scanner.nextInt();
        scanner.nextLine();

        FileReaderUtility fileReader = new FileReaderUtility();
        List<String[]> records = fileReader.readFile("Accounts.csv");
        Map<String, Integer> header = fileReader.getHeader();

        switch (userType) {
            case 1:
                handleCustomer(scanner, records, header);
                break;
            case 2:
                System.out.println("Bank manager functionalities are not implemented yet.");
                break;
            case 3:
                UserRegistration.registerNewUser(records);
                break;
            default:
                System.out.println("Invalid option. Please restart the program and choose a valid option.");
        }

        scanner.close();
    }

    /**
     * Handles customer operations by prompting the user for specific actions.
     * 
     * @param scanner the scanner to receive user input
     * @param records the list of user records for retrieving and updating data
     */
    public static void handleCustomer(Scanner scanner, List<String[]> records, Map<String, Integer> header) {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        Customer customer = findCustomer(records, header, firstName, lastName);

        if (customer == null) {
            System.out.println("Customer not found. Please try again.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome, " + customer.getName() + "! What would you like to do?");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Pay Someone"); // New option
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bankService.checkBalance(customer);
                    break;
                case 2:
                    bankService.deposit(customer, scanner, records);
                    break;
                case 3:
                    bankService.withdrawal(customer, scanner, records);
                    break;
                case 4:
                    bankService.transfer(customer, scanner, records);
                    break;
                case 5:
                    bankService.paySomeone(customer, scanner, records, header); // New method
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using our service. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    /**
     * Searches for a customer in the CSV records based on first and last names.
     * 
     * @param records   the list of user records
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @return the Customer object if found, null otherwise
     */
    public static Customer findCustomer(List<String[]> records, Map<String, Integer> header, String firstName, String lastName) {
        Map<String, Integer> head = header;
        for (String[] record : records) {
            if (record[head.get("First Name")].trim().equalsIgnoreCase(firstName) && record[head.get("Last Name")].trim().equalsIgnoreCase(lastName)) {

                double checkingBalance = Double.parseDouble(record[head.get("Checking Starting Balance")]);
                double savingsBalance = Double.parseDouble(record[head.get("Savings Starting Balance")]);
                double creditBalance = Double.parseDouble(record[head.get("Credit Starting Balance")]);
                double creditLimit = Double.parseDouble(record[head.get("Credit Max")]);
                Checking checking = new Checking(checkingBalance);
                Savings savings = new Savings(savingsBalance);
                Credit credit = new Credit(creditBalance, creditLimit);

                return new Customer(firstName + " " + lastName, record[0], checking, savings, credit);
            }
        }
        return null;
    }
}
