
import java.util.List;
import java.util.Scanner;


/**
 * @author Saul Gonzalez
 * The RunBank class is the main class for the application. Where all teh classes and methods come
 * together. It will cover user interaction, transactions, and logs.
 */

public class RunBank {

    /**
    * The main method where the program starts
    * 
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Miners Bank!!");

        System.out.println("Please select an option below: ");
        System.out.println("1. Customer");
        System.out.println("2. Bank Manager");
        System.out.print("Choose an option: ");
        int userType = scanner.nextInt();
        scanner.nextLine();

        if (userType == 1) {
            
        System.out.print("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please enter your last name: ");
        String lastName = scanner.nextLine();

        // Checks for user in the csv file
        List<String[]> accounts = FileReader.readFile("Accounts.csv");
        Customer customer = null;
        
        for (String[] record : accounts) { //for (int i = 0; i < records.size(); i++) { String[] record = records.get(i);
            String csvFirstName = record[1].trim();
            String csvLastName = record[2].trim();
            
            if (csvFirstName.equalsIgnoreCase(firstName) && csvLastName.equalsIgnoreCase(lastName)) { //ignores upper or lowercase

                // Loads account info from the csv file
                double checkingBalance = Double.parseDouble(record[8]);
                double savingsBalance = Double.parseDouble(record[10]);
                double creditBalance = Double.parseDouble(record[13]);
                double creditMax = Double.parseDouble(record[12]);
                
                //creating objects
                Checking checking = new Checking(checkingBalance);
                Savings savings = new Savings(savingsBalance);
                Credit credit = new Credit(creditBalance, creditMax);

                customer = new Customer(csvFirstName + " " + csvLastName, record[0], checking, savings, credit);
                break;
            }
        }

        //if customer is not found... try again
        if (customer == null) {
            System.out.println("Customer not found. Please try again.");
            return;
        }

        // Bank choices
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Welcome to Miners Bank, " + customer.getName() + "! What would you like to do?");
            System.out.println("1. Check All Balances");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();


            //choices with the methods being called
            switch (choice) {
                case 1:
                    checkBalance(customer);
                    break;
                case 2:
                    MinersDepositMenu(customer, scanner);
                    break;
                case 3:
                    MinersWithdrawMenu(customer, scanner);
                    break;
                case 4:
                    transferFunds(customer, scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using Miners Bank!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    

        //bank manager (not implemented)
} else if (userType == 2) {
    System.out.println("Bank manager options not implemented yet. Please restart the program.");
} else {
System.out.println("ERROR - invalid option. Please restart the program and choose a valid option.");
}
}

    /**
     * Displays the balance of the customer's accounts.
     * 
     * @param customer the customer whose balance will be checked 
     */

    public static void checkBalance(Customer customer) {
        System.out.println();
        System.out.println("--- Account Balances ---");
        System.out.println();
        System.out.println("------------------------");
    
    if (customer.getCheckingAccount() != null) {
        System.out.println("Checking: " + customer.getCheckingAccount().getBalance());
    } else {
        System.out.println("No Checking Account found.");
    }

    if (customer.getSavingsAccount() != null) {
        System.out.println("Savings: " + customer.getSavingsAccount().getBalance());
    } else {
        System.out.println("No Savings Account found.");
    }

    if (customer.getCreditAccount() != null) {
        System.out.println("Credit: " + customer.getCreditAccount().getBalance());
    } else {
        System.out.println("No Credit Account found.");
    }
}


    /**
     * Method to deposit into an account
     * 
     * @param customer the customer performing the deposit
     * @param scanner the scanner for user input
     */
    public static void MinersDepositMenu(Customer customer, Scanner scanner) {
        System.out.println();
        System.out.println("Which account would you like to deposit into?");
        System.out.println("Please select a number");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. Credit");
        int choice = scanner.nextInt();

        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        switch (choice) {
            case 1:
                customer.getCheckingAccount().deposit(amount);
                System.out.println("Deposited " + amount + " to Checking account.");
                Log.logTransaction("Deposited " + amount + " to Checking account for " + customer.getName()+ ". New balance is: " + customer.getCheckingAccount().getBalance());
                break;
            case 2:
                customer.getSavingsAccount().deposit(amount);
                System.out.println("Deposited " + amount + " to Savings account.");
                Log.logTransaction("Deposited " + amount + " to Savings account for " + customer.getName() + ". New balance is: " + customer.getSavingsAccount().getBalance());
                break;
            case 3:
                customer.getCreditAccount().deposit(amount);
                System.out.println("Deposited " + amount + " to Credit account.");
                break;
            default:
                System.out.println("Invalid account choice.");
        }
    }


    /**
     * Method for withdrawing into an account
     * 
     * @param customer the customer performing the withdrawal
     * @param scanner the scanner for user input
     */
    public static void MinersWithdrawMenu(Customer customer, Scanner scanner) {
        System.out.println("\nWhich account would you like to withdraw from?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. Credit");
        int choice = scanner.nextInt();

        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        switch (choice) {
            case 1:
                if (customer.getCheckingAccount().withdraw(amount)) {
                    System.out.println("Withdrew " + amount + " from Checking account.");
                    Log.logTransaction("Deposited " + amount + " to Checking account for " + customer.getName()+ ". New balance is: " + customer.getCheckingAccount().getBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
                break;
            case 2:
                if (customer.getSavingsAccount().withdraw(amount)) {
                    System.out.println("Withdrew " + amount + " from Savings account.");
                    Log.logTransaction("Deposited " + amount + " to Savings account for " + customer.getName() + ". New balance is: " + customer.getSavingsAccount().getBalance());
                } else {
                    System.out.println("Insufficient funds");
                }
                break;
            case 3:
                if (customer.getCreditAccount().withdraw(amount)) {
                    System.out.println("Withdrew " + amount + " from Credit account.");
                } else {
                    System.out.println("Credit limit has been exceeded.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


    /**
     * Transfers funds between the customer's checking and savings accounts.
     * 
     * @param customer the customer performing the transfer
     * @param scanner the scanner for user input
     */
    public static void transferFunds(Customer customer, Scanner scanner) {
        System.out.println();
        System.out.println("Transfer from which account?");
        System.out.println("Please select a number below");
        System.out.println("-----------------------------");
        System.out.println("1. Checking to Savings");
        System.out.println("2. Savings to Checking");
        int choice = scanner.nextInt();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        switch (choice) {
            case 1:
                if (customer.getCheckingAccount().withdraw(amount)) {
                    customer.getSavingsAccount().deposit(amount);
                    System.out.println("Transferred " + amount + " from Checking to Savings.");
                    Log.logTransaction("Deposited " + amount + " to Checking account for " + customer.getName()+ ". New balance is: " + customer.getCheckingAccount().getBalance());
                } else {
                    System.out.println("Insufficient funds in Checking account.");
                }
                break;
            case 2:
                if (customer.getSavingsAccount().withdraw(amount)) {
                    customer.getCheckingAccount().deposit(amount);
                    System.out.println("Transferred " + amount + " from Savings to Checking.");
                    Log.logTransaction("Deposited " + amount + " to Savings account for " + customer.getName() + ". New balance is: " + customer.getSavingsAccount().getBalance());
                } else {
                    System.out.println("Insufficient funds in Savings account.");
                }
                break;
            default:
                System.out.println("Invalid transfer choice.");
        }
    }
}
