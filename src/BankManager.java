import java.util.List;
import java.util.Map;

//import org.w3c.dom.views.DocumentView;

/**
 * Reads the Transactions.csv file and processes all transactions.
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 */
public class BankManager {
    public static void transactionReader(String transFile, List<String[]> records, Map<String, Integer> accHeader) {
        FileReaderUtility fileReader = new FileReaderUtility();
        List<String[]> trans = fileReader.readFile(transFile);
        Map<String, Integer> header = fileReader.getHeader();
        int count = 1;

        for (String[] record : trans) {
            //System.out.println("p");
            String action = record[header.get("Action")].trim();
            String amount = record[header.get("Action Amount")].trim();
            count++;
                        
            if (action.equals("pays")) {
                Double amnt = Double.parseDouble(amount);
                Customer customer1 = RunBank.findCustomer(records, accHeader, record[header.get("From First Name")], record[header.get("From Last Name")]);
                Customer customer2 = RunBank.findCustomer(records, accHeader, record[header.get("To First Name")], record[header.get("To Last Name")]);
                String from = record[header.get("From Where")];
                String to = record[header.get("To Where")];

                switch (from) {
                    case ("Checking"):
                        customer1.getCheckingAccount().withdraw(amnt);
                        break;
                    case ("Savings"):
                        customer1.getSavingsAccount().withdraw(amnt);
                        break;
                    case ("Credit"):
                        customer1.getCreditAccount().withdraw(amnt);
                        break;
                }

                switch (to) {
                    case ("Checking"):
                        customer2.getCheckingAccount().deposit(amnt);
                        break;
                    case ("Savings"):
                        customer2.getSavingsAccount().deposit(amnt);
                        break;
                    case ("Credit"):
                        customer2.getCreditAccount().deposit(amnt);
                        break;
                }
            }
            else if (action.equals("transfer")) {
                Double amnt = Double.parseDouble(amount);
                Customer customer1 = RunBank.findCustomer(records, accHeader, record[header.get("From First Name")], record[header.get("From Last Name")]);
                String from = record[header.get("From Where")];
                String to = record[header.get("To Where")];
                switch (from) {
                    case ("Checking"):
                        customer1.getCheckingAccount().withdraw(amnt);
                        break;
                    case ("Savings"):
                        customer1.getSavingsAccount().withdraw(amnt);
                        break;
                    case ("Credit"):
                        customer1.getCreditAccount().withdraw(amnt);
                        break;
                }

                switch (to) {
                    case ("Checking"):
                        customer1.getCheckingAccount().deposit(amnt);
                        break;
                    case ("Savings"):
                        customer1.getSavingsAccount().deposit(amnt);
                        break;
                    case ("Credit"):
                        customer1.getCreditAccount().deposit(amnt);
                        break;
                }
            }
            else if (action.equals("withdraws")) {
                Customer customer1 = RunBank.findCustomer(records, accHeader, record[header.get("From First Name")], record[header.get("From Last Name")]);
                String from = record[header.get("From Where")];
                Double amnt = Double.parseDouble(amount);

                switch (from) {
                    case ("Checking"):
                        customer1.getCheckingAccount().withdraw(amnt);
                        break;
                    case ("Savings"):
                        customer1.getSavingsAccount().withdraw(amnt);
                        break;
                    case ("Credit"):
                        customer1.getCreditAccount().withdraw(amnt);
                        break;
                }
            }

            else if (action.equals("deposits")) {
                Customer customer2 = RunBank.findCustomer(records, accHeader, record[header.get("To First Name")], record[header.get("To Last Name")]);
                String to = record[header.get("To Where")];
                Double amnt = Double.parseDouble(amount);

                switch (to) {
                    case ("Checking"):
                        customer2.getCheckingAccount().deposit(amnt);
                        break;
                    case ("Savings"):
                        customer2.getSavingsAccount().deposit(amnt);
                        break;
                    case ("Credit"):
                        customer2.getCreditAccount().deposit(amnt);
                }
            }
            else {
                System.out.println("Transaction #" +count+ " failed: Invalid action");
            }
        }
        System.out.println("Transactions successful.");
    }
}
