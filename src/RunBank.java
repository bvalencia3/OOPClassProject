import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the miners banking system.
 * Manages user interactions and allows different banking operations.
 * 
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 */
public class RunBank {

    private static BankService bankService = new BankService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Miners Bank");
        System.out.println("Are you a:");
        System.out.println("1. Existing Customer");
        System.out.println("2. Bank Manager");
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
                BankManager bankManager = new BankManager();
                bankManager.handleBankManager(scanner, records, header);
                break;
            default:
                System.out.println("Invalid option. Please restart the program and choose a valid option.");
        }

        scanner.close();
    }

    private static void handleCustomer(Scanner scanner, List<String[]> records, Map<String, Integer> header) {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        Customer customer = findCustomer(records, header, firstName, lastName);

        if (customer == null) {
            System.out.println("Customer not found. Please try again.");
            return;
        }

        // Prompt for password
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Check if password is empty
        if (password.isEmpty()) {
            System.out.println("Access denied. Password is required.");
            return;
        }

        // Proceed with account access
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome, " + customer.getName() + "! What would you like to do?");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Pay Someone");
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
                    bankService.paySomeone(customer, scanner, records, header);
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

    public static Customer findCustomer(List<String[]> records, Map<String, Integer> header, String firstName,
            String lastName) {
        for (String[] record : records) {
            if (record[header.get("First Name")].trim().equalsIgnoreCase(firstName)
                    && record[header.get("Last Name")].trim().equalsIgnoreCase(lastName)) {
                double checkingBalance = Double.parseDouble(record[header.get("Checking Starting Balance")]);
                double savingsBalance = Double.parseDouble(record[header.get("Savings Starting Balance")]);
                double creditBalance = Double.parseDouble(record[header.get("Credit Starting Balance")]);
                double creditLimit = Double.parseDouble(record[header.get("Credit Max")]);
                Checking checking = new Checking(checkingBalance);
                Savings savings = new Savings(savingsBalance);
                Credit credit = new Credit(creditBalance, creditLimit);

                return new Customer(firstName + " " + lastName, record[0], checking, savings, credit);
            }
        }
        return null;
    }
}
