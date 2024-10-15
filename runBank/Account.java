package runBank;

public class Account { //Make abstract
	
	 double balance;
	 String customerName;
	 String customerId;

	 // Constructor

	 Account(String cname, String cId) {
	     customerName = cname;
	     customerId = cId;
	 }

	 
	 //Method will deposit money into account and update
	 void deposit(double amount) {
	     if (amount != 0) {
	         balance += amount;
	     }
	 }

	 //method will withdraw money from account and update
	 void withdraw(double amount) {
	     if (amount != 0) {
	            balance -= amount;
	     }
	 }

}
