package project;

import java.util.Scanner;


/**
 * Main class that will run the menu and offer user interaction
 * It provides other methods and calls actions from the other classes to gather 
 * information for the program to function
 */
public class RunBank {
    private Repository repository;

    public RunBank() {
        this.repository = new Repository();
    }

    // Main method to start interaction with the user
    public static void main(String[] args) {
        RunBank runBank = new RunBank();
        runBank.menu();
    }

    /**
     * Menu method will hold the options the user will use to interact
     * as well as the initial bank greeting
     */
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Miners Bank!");
        System.out.println("Are you a Customer or Bank Manager?");

        String userResponse = scanner.nextLine().toLowerCase();

        if (userResponse.equals("customer")) {
            System.out.println("Please enter your first name:");
            String firstName = scanner.nextLine();
            System.out.println("Please enter your last name:");
            String lastName = scanner.nextLine();
            Customer customer = new Customer(firstName, lastName, generateCustomerId());

            
            while (true) { //run while true
            	System.out.println("Please select an option below: (Numerical)");
                System.out.println("1. Inquire Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Transfer Money Between Accounts");
                System.out.println("5. Pay Someone");
                System.out.println("6. Exit");
                System.out.println();
                
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        balance(customer);
                        break;
                    case 2:
                        deposit(customer);
                        break;
                    case 3:
                        withdraw(customer);
                        break;
                    case 4:
                        transfer(customer);
                        break;
                    case 5:
                        paySomeone(customer);
                        break;
                    case 6:
                        System.out.println("Thank you for using Miners Bank! ");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Bank Manager Features Coming Soon!");
        }
        scanner.close();
    }

    /**
     * balance method that will let the customer know the balance in either the
     * checkings, credit, or savings
     * 
     */
    private void balance(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter account type (checking/savings/credit):");
        System.out.println();
        String accountType = scanner.nextLine().toLowerCase();
        Account account = getAccountForCustomer(accountType, customer);
        if (account != null) {
            System.out.println("The balance for " + accountType + " account is: $" + account.getBalance());
        } else {
            System.out.println("Account was not found.");
        }
    }

    /**
     * Deposit method where the program will prompt the user where the deposit
     * will take place
     */
    private void deposit(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter account type (checking/savings/credit):");
        System.out.println();
        String accountType = scanner.nextLine().toLowerCase();
        Account account = getAccountForCustomer(accountType, customer);
        if (account != null) {
            System.out.println("Enter amount to deposit:");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit of $" + amount + " was successful to your " + accountType + " account.");
        } else {
            System.out.println("Account was not found.");
        }
    }

    /**
     * withdraw method where the pprogram will ask the user where to withdraw the money from
     */
    private void withdraw(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account type (checking/savings/credit):");
        String accountType = scanner.nextLine().toLowerCase();

        Account account = getAccountForCustomer(accountType, customer);
        if (account != null) {
            System.out.println("Enter amount to withdraw:");
            double amount = scanner.nextDouble();
                account.withdrawal(amount);
                System.out.println("Withdrawal of $" + amount + " was successful from your " + accountType + " account.");
        } else {
            System.out.println("Account was not found.");
        }
    }

    /**
     * Method to transfer money between accounts
     */
    private void transfer(Customer customer) {
    	
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which account would you like to send from? (checking/savings/credit):");
        String fromAccount = scanner.nextLine().toLowerCase();
        System.out.println("Which account would you like to send to? (checking/savings/credit):");
        String toAccount = scanner.nextLine().toLowerCase();
        Account sourceAccount = getAccountForCustomer(fromAccount, customer);
        Account destinationAccount = getAccountForCustomer(toAccount, customer);

        if (sourceAccount != null && destinationAccount != null) {
            System.out.println("Enter amount to transfer:");
            double amount = scanner.nextDouble();
            sourceAccount.withdrawal(amount);
            destinationAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Account(s) not found.");
        }
    }

    /**
     * Method where the program will ask the user if he or she wants to pay someone
     */
    private void paySomeone(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the your last name:");
        String lastName = scanner.nextLine();
        Customer payOther = new Customer(firstName, lastName, generateCustomerId());

        System.out.println("Enter the amount to pay:");
        double amount = scanner.nextDouble();

        Account sourceAccount = getAccountForCustomer("checking", customer);
        if (sourceAccount != null) {
            try {
                sourceAccount.withdrawal(amount);
                System.out.println("Payment successful!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Your account not found.");
        }
    }

    /**
     * method to get account for the customer based on account type
     */
    
    private Account getAccountForCustomer(String accountType, Customer customer) {
        if (accountType.equals("checking")) {
            return new Checking(0, 0);
        } else if (accountType.equals("savings")) {
            return new Savings(0,0, 0);
        } else if (accountType.equals("credit")) {
            return new Credit(0,0,0);
        }
        return null;
    }

    /**
     * method to get customer ID and account number
     */    private int generateAccountNumber() {
        return 0;
    }

    private int generateCustomerId() {
		return 0;
        
    }
}
