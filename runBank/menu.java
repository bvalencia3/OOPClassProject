package runBank;
import java.util.Scanner;


//Only the main menu
public class menu {
	
	void menu() {

        char option = 0;

        Scanner sc = new Scanner(System.in);

        
        //Menu for the beginning
        
        System.out.println("Welcome to miners bank! " ); //NEED add customer name
        System.out.println("Your ID is: " ); //NEED add customer ID
        System.out.println();
        System.out.println("A. Transfer Money");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Send Money To Someone");
        System.out.println("E. Current Balance");
        System.out.println("F. Exit");

        do {

            System.out.println("");
            System.out.println("Please select from the following options");
            System.out.println("");
            option = sc.next().charAt(0);
            option = Character.toUpperCase(option);
            switch (option) {

                case 'A':
                System.out.println("");
                System.out.println("How much would you like to transfer?: " ); //Add balance function
                System.out.println("");
                System.out.println();
                break;
                
        } while (option != 'F');

    }

}
