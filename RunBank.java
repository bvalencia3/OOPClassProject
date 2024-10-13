import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RunBank {
    // Main Method

    public static void main(String[] args) {

        System.out.println("Please enter your first and last name");

        Scanner userName = new Scanner(System.in);

        String name = userName.nextLine();

        // object of the class

        Account bank1 = new Account(name, "");

        bank1.appMenu();

    }

}

// class CSVReader {
// String path = "";
// String line = "";
//
// try {
// BufferedReader br = new BufferedReader (new FileReader (path));
//
// while ((line = br.readLine() )) !=null){
// String [] values = line.split(",");
// System.out.println(values[0]);
// }
// catch (FileNotFoundException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
//
// }

// class Customer {

// String userName;
// String password;
// int pin;
// int routNum;

// public Customer() {

// }

// }

class Account {
    int balance;
    String customerName;
    String customerId;

    // Constructor

    Account(String cname, String cId) {
        customerName = cname;
        customerId = cId;
    }

    void deposit(int amount) {
        if (amount != 0) {
            balance = balance + amount;
        }
    }

    void withdraw(int amount) {
        if (amount != 0) {
            balance = balance - amount;
        }
    }

    void appMenu() {

        char option = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to miners bank! " + customerName);
        System.out.println("Your ID is: " + customerId);
        System.out.println();
        System.out.println("A. Transfer Money");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Send Money To Someone");
        System.out.println("E. Current Balance");
        System.out.println("F. Exit");

        do {

            System.out.println("=================");
            System.out.println("Please select from the following options");
            System.out.println("=================");
            option = sc.next().charAt(0);
            option = Character.toUpperCase(option);
            switch (option) {

                // case 'A':
                // System.out.println("================");
                // System.out.println("How much would you like to transfer?: " + balance);
                // System.out.println("================");
                // System.out.println();
                // break;
                case 'B':
                    System.out.println("================");
                    System.out.println("Enter a deposit amount: ");
                    System.out.println("================");
                    int amount = sc.nextInt();
                    deposit(amount);
                    System.out.println();
                    break;
                case 'C':
                    System.out.println("================");
                    System.out.println("How much will you withdraw?: ");
                    System.out.println("================");
                    int amount2 = sc.nextInt();
                    withdraw(amount2);
                    System.out.println();
                    break;
                case 'D':
                    break;
                case 'E':
                    System.out.println("================");
                    System.out.println("Your Balance is " + balance);
                    System.out.println("================");
                    System.out.println();
                    break;
                case 'F':
                    System.out.println("================");
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }
        } while (option != 'F');

    }
}
