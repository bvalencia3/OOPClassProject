
import java.util.List;
import java.util.Scanner;

public class RunBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Miners Bank!!");

        System.out.println("Are you a:");
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
        List<String[]> accounts = FileReaderUtility.readFile("Accounts.csv");
        Customer customer = null;
        
        for (String[] record : accounts) { //for (int i = 0; i < records.size(); i++) { String[] record = records.get(i);
            String csvFirstName = record[1].trim();
            String csvLastName = record[2].trim();
            
            if (csvFirstName.equalsIgnoreCase(firstName) && csvLastName.equalsIgnoreCase(lastName)) {

                // Loads account info from the csv file
                double checkingBalance = Double.parseDouble(record[8]);
                double savingsBalance = Double.parseDouble(record[10]);
                double creditBalance = Double.parseDouble(record[13]);
                double creditMax = Double.parseDouble(record[12]);
                
                Checking checking = new Checking(checkingBalance);
                Savings savings = new Savings(savingsBalance);
                Credit credit = new Credit(creditBalance, creditMax);

                customer = new Customer(csvFirstName + " " + csvLastName, record[0], checking, savings, credit);
                break;
            }
        }

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

            switch (choice) {
                case 1:
                    checkBalance(customer);
                    break;
                case 2:
                    performDeposit(customer, scanner);
                    break;
                case 3:
                    performWithdrawal(customer, scanner);
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
    

} else if (userType == 2) {
    // Bank manager flow
    System.out.println("Bank manager options not implemented yet. Please restart the program.");
} else {
    
System.out.println("ERROR - invalid option. Please restart the program and choose a valid option.");
}
}


    public static void checkBalance(Customer customer) {
        System.out.println("\n--- Account Balances ---");
    
    if (customer.getCheckingAccount() != null) {
        System.out.println("Checking: " + customer.getCheckingAccount().getBalance());
    } else {
        System.out.println("Checking: No Checking Account found.");
    }

    if (customer.getSavingsAccount() != null) {
        System.out.println("Savings: " + customer.getSavingsAccount().getBalance());
    } else {
        System.out.println("Savings: No Savings Account found.");
    }

    if (customer.getCreditAccount() != null) {
        System.out.println("Credit: " + customer.getCreditAccount().getBalance());
    } else {
        System.out.println("Credit: No Credit Account found.");
    }
}

    public static void performDeposit(Customer customer, Scanner scanner) {
        System.out.println("\nWhich account would you like to deposit into?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. Credit");
        int accountChoice = scanner.nextInt();

        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        switch (accountChoice) {
            case 1:
                customer.getCheckingAccount().deposit(amount);
                System.out.println("Deposited " + amount + " to Checking account.");
                break;
            case 2:
                customer.getSavingsAccount().deposit(amount);
                System.out.println("Deposited " + amount + " to Savings account.");
                break;
            case 3:
                customer.getCreditAccount().deposit(amount);
                System.out.println("Deposited " + amount + " to Credit account.");
                break;
            default:
                System.out.println("Invalid account choice.");
        }
    }

    public static void performWithdrawal(Customer customer, Scanner scanner) {
        System.out.println("\nWhich account would you like to withdraw from?");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.println("3. Credit");
        int accountChoice = scanner.nextInt();

        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        switch (accountChoice) {
            case 1:
                if (customer.getCheckingAccount().withdraw(amount)) {
                    System.out.println("Withdrew " + amount + " from Checking account.");
                } else {
                    System.out.println("Insufficient funds in Checking account.");
                }
                break;
            case 2:
                if (customer.getSavingsAccount().withdraw(amount)) {
                    System.out.println("Withdrew " + amount + " from Savings account.");
                } else {
                    System.out.println("Insufficient funds in Savings account.");
                }
                break;
            case 3:
                if (customer.getCreditAccount().withdraw(amount)) {
                    System.out.println("Withdrew " + amount + " from Credit account.");
                } else {
                    System.out.println("Credit limit exceeded.");
                }
                break;
            default:
                System.out.println("Invalid account choice.");
        }
    }

    public static void transferFunds(Customer customer, Scanner scanner) {
        System.out.println("\nTransfer from which account?");
        System.out.println("1. Checking to Savings");
        System.out.println("2. Savings to Checking");
        int transferChoice = scanner.nextInt();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        switch (transferChoice) {
            case 1:
                if (customer.getCheckingAccount().withdraw(amount)) {
                    customer.getSavingsAccount().deposit(amount);
                    System.out.println("Transferred " + amount + " from Checking to Savings.");
                } else {
                    System.out.println("Insufficient funds in Checking account.");
                }
                break;
            case 2:
                if (customer.getSavingsAccount().withdraw(amount)) {
                    customer.getCheckingAccount().deposit(amount);
                    System.out.println("Transferred " + amount + " from Savings to Checking.");
                } else {
                    System.out.println("Insufficient funds in Savings account.");
                }
                break;
            default:
                System.out.println("Invalid transfer choice.");
        }
    }
}
