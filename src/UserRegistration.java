import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserRegistration {

    private static int lastUserId = 1000; // Default starting point if no records exist

    /**
     * Registers a new user by adding their details to the records.
     * 
     * @param records the current list of user records to update with the new user's
     *                information
     */
    public static void registerNewUser(List<String[]> records) {
        initializeLastUserId(records);

        Scanner scanner = new Scanner(System.in);

        String firstName = getInput(scanner, "Enter First Name: ");
        String lastName = getInput(scanner, "Enter Last Name: ");
        String dob = formatDateOfBirth(getInput(scanner, "Enter Date of Birth (DD-MM-YYYY): "));
        String address = collectAddress(scanner);
        String phoneNumber = getInput(scanner, "Enter Phone Number: ");
        int creditScore = Integer.parseInt(getInput(scanner, "Enter Credit Score: "));

        int userId = ++lastUserId; // Increment the last used ID to generate a new one
        double creditLimit = calculateCreditLimit(creditScore);

        // Generate unique account numbers
        int checkingAccountNumber = generateUniqueAccountNumber();
        int savingsAccountNumber = generateUniqueAccountNumber();
        int creditAccountNumber = generateUniqueAccountNumber();

        // Use the factory to create accounts
        Account checking = AccountFactory.createAccount("checking", 15.0, 0);
        Account savings = AccountFactory.createAccount("savings", 15.0, 0);
        Account credit = AccountFactory.createAccount("credit", 0.0, creditLimit);

        saveUserToCSV(records, userId, firstName, lastName, dob, address, phoneNumber,
                checkingAccountNumber, savingsAccountNumber, creditAccountNumber,
                checking.getBalance(), savings.getBalance(), credit.getBalance(), creditLimit);

        System.out.println("User created successfully with User ID: " + userId);
    }

    /**
     * Initializes the last used user ID by scanning the existing records.
     * 
     * @param records the current list of user records
     */
    private static void initializeLastUserId(List<String[]> records) {
        for (String[] record : records) {
            try {
                int userId = Integer.parseInt(record[0].trim());
                if (userId > lastUserId) {
                    lastUserId = userId;
                }
            } catch (NumberFormatException e) {
                // Ignore invalid user IDs
            }
        }
    }

    private static String getInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static String formatDateOfBirth(String dobInput) {
        return dobInput; // Simplified for brevity
    }

    private static String collectAddress(Scanner scanner) {
        String street = getInput(scanner, "Enter Street Address: ");
        String city = getInput(scanner, "Enter City: ");
        String state = getInput(scanner, "Enter State: ");
        String zip = getInput(scanner, "Enter Zip Code: ");
        return "\"" + street + ", " + city + ", " + state + " " + zip + "\""; // Address wrapped in quotes
    }

    private static int generateUniqueAccountNumber() {
        return 1000 + (int) (Math.random() * 9000); // Simplified for brevity
    }

    private static double calculateCreditLimit(int creditScore) {
        if (creditScore <= 580) {
            return 500;
        } else if (creditScore <= 670) {
            return 2000;
        } else if (creditScore <= 740) {
            return 5000;
        } else {
            return 10000;
        }
    }

    private static void saveUserToCSV(List<String[]> records, int userId, String firstName, String lastName, String dob,
            String address, String phoneNumber, int checkingAccountNum, int savingsAccountNum, int creditAccountNum,
            double checkingBalance, double savingsBalance, double creditBalance, double creditLimit) {
        String[] newUserRecord = {
                String.valueOf(userId),
                firstName,
                lastName,
                dob,
                address,
                phoneNumber,
                String.valueOf(checkingAccountNum),
                String.valueOf(checkingBalance),
                String.valueOf(savingsAccountNum),
                String.valueOf(savingsBalance),
                String.valueOf(creditAccountNum),
                String.valueOf(creditBalance),
                String.valueOf(creditLimit)
        };

        records.add(newUserRecord);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.csv", true))) {
            for (int i = 0; i < newUserRecord.length; i++) {
                writer.write(newUserRecord[i]);
                if (i < newUserRecord.length - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
