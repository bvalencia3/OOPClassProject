package project;

import java.util.Scanner;

public class RunBank {
    // Main Method

    public static void main(String[] args) {
    	
    	Repository repo = new Repository();
    	Scanner scanner = new Scanner (System.in);
    	Checking checkingAccount = new Checking(0, 0);
    	Savings savingsAccount = new Savings(0, 0, 0);
    	Credit creditAccount = new Credit(0, 0, 0);

        System.out.println("Please enter your first and last name");
        
        
        
        while (true) {
        	System.out.println("Welcome to miners bank!!");
        	System.out.println("Please select one of the following options");
        	System.out.println("1. Deposit");
        	System.out.println("2. Withdraw");
        	System.out.println("3. Check Balance");
        	System.out.println("4. Exit");
        	
        	int option = scanner.nextInt();
        	scanner.nextLine();
        	
        	
        	//deposit amount
        	switch (option) {
        	case 1: 
        		System.out.println("Enter the account you'd like to use (Checking, Savings, Credit)");
        		String typeChecking = scanner.nextLine().toLowerCase();
        		System.out.println("Enter deposit ammount: ");
        		double amountDeposit = scanner.nextDouble();
        		
        		if (typeChecking.equals("Checking")) {
        			checkingAccount.deposit(amountDeposit);
        		} else if (typeChecking.equals("Savings")) {
        			savingsAccount.deposit(amountDeposit);
        		} else if (typeChecking.equals("Credit")) {
        			creditAccount.deposit(amountDeposit);
        		} else {
        			System.out.println("Invalid account type");
        		}
        		break;
        		
        		
        	
        	//withdraw amount
        	case 2:
        		System.out.println();
        		String typeWithdraw = scanner.nextLine().toLowerCase();
        		System.out.println("Enter withdraw amount: ");
        		double withdrawAmount = scanner.nextDouble();
        		
        		if (typeWithdraw.equals("Checking")) {
        			checkingAccount.deposit(withdrawAmount);
        		} else if (typeWithdraw.equals("Savings")) {
        			savingsAccount.deposit(withdrawAmount);
        		} else if (typeWithdraw.equals("Credit")) {
        			creditAccount.deposit(withdrawAmount);
        		} else {
        			System.out.println("Invalid account type");
        		}
        		break;
        		
        		
        		//Check balance
        	case 3:
        		System.out.println("Which account would you like to see? (Checking, Savings, or Credit? ");
        		String typeBalance = scanner.nextLine().toLowerCase();
        		if (typeBalance.equals("Checking")) {
        			System.out.println("Checkings account Balance is: " + checkingAccount.getBalance());
        			System.out.println();
        		}else if
        			(typeBalance.equals("Savings")) {
            			System.out.println("Savings account Balance is: " + savingsAccount.getBalance());
            			System.out.println();
        		}else if
        			(typeBalance.equals("Credit")) {
            			System.out.println("Credit account Balance is: " + creditAccount.getBalance());
            			System.out.println();
        		
        		} else {
        			System.out.println("Invalid account type");
        		}
        		break;
        		
        		
        		
        		//exit
        	case 4: 
        		System.out.println("Thank you for using Miners Bank");
        		scanner.close();
        		return;   	
        	}	
        }
 
    }

}
