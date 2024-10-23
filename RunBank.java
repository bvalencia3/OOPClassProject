package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RunBank {
    // Main Method

    public static void main(String[] args) {
    	
		System.out.println("Please enter your first and last name");
		Scanner scanner = new Scanner (System.in);
		Customer customer = new Customer();
		customer.setFirstName(scanner.next());
		customer.setLastName(scanner.nextLine());

		String fileName = "BankUsers.csv";
        String delimiter = ",";
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		int firstNameIndex = 1;
		boolean found = false;
		String[] row = null;

		for (int i = 1; i < data.size(); i++) {
            row = data.get(i);
            if (row.length > firstNameIndex && row[firstNameIndex].trim().equalsIgnoreCase(customer.getFirstName().trim())) {
                if (row[firstNameIndex + 1].trim().equalsIgnoreCase(customer.getLastName().trim())) {
                found = true;
                break;
            }
		}
        }

    	//Repository repo = new Repository();
    	//Checking checkingAccount = new Checking(0, 0);
    	//Savings savingsAccount = new Savings(0, 0, 0);
    	//Credit creditAccount = new Credit(0, 0, 0);
        
        if (found) {
			Checking checkingAccount = new Checking(Long.parseLong(row[8].trim()), Integer.parseInt(row[0].trim()));
			checkingAccount.deposit(Double.parseDouble(row[9].trim()));
			System.out.println(checkingAccount.getBalance());
    		Savings savingsAccount = new Savings(Long.parseLong(row[10].trim()), Integer.parseInt(row[0].trim()), 0);
			savingsAccount.deposit(Double.parseDouble(row[11].trim()));
    		Credit creditAccount = new Credit(Long.parseLong(row[12].trim()), Integer.parseInt(row[0].trim()), Double.parseDouble(row[13].trim()));
			//creditAccount.deposit(Double.parseDouble(row[14]));

        	System.out.println("Welcome to miners bank, " +customer.getFirstName());
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
        		
        		if (typeChecking.equals("checking")) {
        			checkingAccount.deposit(amountDeposit);
        		} else if (typeChecking.equals("savings")) {
        			savingsAccount.deposit(amountDeposit);
        		} else if (typeChecking.equals("credit")) {
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
        		
        		if (typeWithdraw.equals("checking")) {
        			checkingAccount.deposit(withdrawAmount);
        		} else if (typeWithdraw.equals("savings")) {
        			savingsAccount.deposit(withdrawAmount);
        		} else if (typeWithdraw.equals("credit")) {
        			creditAccount.deposit(withdrawAmount);
        		} else {
        			System.out.println("Invalid account type");
        		}
        		break;
        		
        		
        		//Check balance
        	case 3:
        		System.out.println("Which account would you like to see? (Checking, Savings, or Credit? ");
        		String typeBalance = scanner.nextLine().toLowerCase();
        		if (typeBalance.equals("checking")) {
        			System.out.println("Checkings account Balance is: " + checkingAccount.getBalance());
        			System.out.println();
        		}else if
        			(typeBalance.equals("savings")) {
            			System.out.println("Savings account Balance is: " + savingsAccount.getBalance());
            			System.out.println();
        		}else if
        			(typeBalance.equals("credit")) {
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
		else {
			System.out.println("User not Found.");
		}
		
    }
}
