import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Handles user registration process.
 * Provides methods to register a new user and save their information.
 */
public class UserRegistration {

    private static final String CSV_FILE_PATH = "Accounts.csv";
    private static int lastUserId = 1000; // Starting user ID if CSV is empty
    private static final Set<Integer> existingAccountNumbers = new HashSet<>(); // To track unique account numbers
    private static final Random random = new Random();

    // Display prompt and read input - A way to minimize amount of code and can be
    // reused as a method
    private static String getInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Registers a new user by adding their details to the records.
     * 
     * @param records the current list of user records to update with the new user's
     *                information
     */
    public static void registerNewUser(List<String[]> records) {
        Scanner scanner = new Scanner(System.in);

        String firstName = getInput(scanner, "Enter First Name: ");
        String lastName = getInput(scanner, "Enter Last Name: ");
        String dob = formatDateOfBirth(getInput(scanner, "Enter Date of Birth (DD-MM-YYYY): "));
        String address = collectAddress(scanner);
        String phoneNumber = getInput(scanner, "Enter Phone Number: ");
        int creditScore = Integer.parseInt(getInput(scanner, "Enter Credit Score: "));

        int userId = generateUniqueUserId();
        int checkingAccountNumber = generateUniqueAccountNumber();
        int savingsAccountNumber = generateUniqueAccountNumber();
        int creditAccountNumber = generateUniqueAccountNumber();
        double creditLimit = calculateCreditLimit(creditScore);

        saveUserToCSV(records, userId, firstName, lastName, dob, address, phoneNumber,
                checkingAccountNumber, savingsAccountNumber, creditAccountNumber, creditLimit);

        System.out.println("User created successfully with User ID: " + userId);
    }

    // Collect and format the address
    private static String collectAddress(Scanner scanner) {
        String street = getInput(scanner, "Enter Street Address: ");
        String city = getInput(scanner, "Enter City: ");
        String state = getInput(scanner, "Enter State: ");
        String zip = getInput(scanner, "Enter Zip Code: ");
        return "\"" + street + ", " + city + ", " + state + " " + zip + "\""; // Address wrapped in quotes
    }

    private static final SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy"); // New input format
    private static final SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-yy"); // Desired output format

    // Format the date of birth to "DD-MMM-YY (02-MAY-95)"
    private static String formatDateOfBirth(String dobInput) {
        try {
            Date date = inputDateFormat.parse(dobInput); // input date
            return outputDateFormat.format(date); // Format to new output
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            return dobInput; // Return the original input if invalid format
        }
    }

    // Write user data to CSV
    private static void saveUserToCSV(List<String[]> records, int userId, String firstName, String lastName, String dob,
            String address,
            String phoneNumber, int checkingAccountNumber, int savingsAccountNumber, int creditAccountNumber,
            double creditLimit) {
        String[] newUserRecord = {
                String.valueOf(userId),
                firstName,
                lastName,
                dob,
                address,
                phoneNumber,
                String.valueOf(checkingAccountNumber),
                "15.0",
                String.valueOf(savingsAccountNumber),
                "15.0",
                String.valueOf(creditAccountNumber),
                "0.0",
                String.valueOf(creditLimit)
        };

        records.add(newUserRecord);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
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

    // Generate a unique 4 digit account number and ensures no duplicates
    private static int generateUniqueAccountNumber() {
        int accountNumber;
        do {
            // Generates a random number from 1000 to 9999
            accountNumber = 1000 + random.nextInt(9000);
        } while (existingAccountNumbers.contains(accountNumber));

        existingAccountNumbers.add(accountNumber);
        return accountNumber;
    }

    // Calculate credit limit based on credit score
    // Uses random import
    private static double calculateCreditLimit(int creditScore) {
        if (creditScore <= 580) {
            return 100 + random.nextInt(600);
        } else if (creditScore <= 669) {
            return 700 + random.nextInt(4300);
        } else if (creditScore <= 739) {
            return 5000 + random.nextInt(2500);
        } else if (creditScore <= 799) {
            return 7500 + random.nextInt(8500);
        } else {
            return 16000 + random.nextInt(9000);
        }
    }

    // Initialize user ID and account numbers from CSV
    // Ensure that lastUserId starts with the highest ID found in the CSV file
    static {
        accountNumbers();
    }

    private static void accountNumbers() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CSV_FILE_PATH));
            boolean skipHeader = lines.get(0).toLowerCase().contains("identification number");

            int startIndex;
            if (skipHeader) {
                startIndex = 1;
            } else {
                startIndex = 0;
            }

            for (int i = startIndex; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] values = line.split(",");

                if (values.length >= 1) {
                    try {
                        int userId = Integer.parseInt(values[0].trim());
                        if (userId > lastUserId) {
                            lastUserId = userId;
                        }
                    } catch (NumberFormatException e) {
                        // Skip line if number parsing fails
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file for user IDs: " + e.getMessage());
        }
    }

    // Generate a unique User ID that increments by 1 after creating the user
    private static int generateUniqueUserId() {
        lastUserId += 1;
        return lastUserId;
    }
}
