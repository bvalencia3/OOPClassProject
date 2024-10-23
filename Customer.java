package project;

/**
 * A subclass of Person, representing a Customer
 * @author Gian Hernandez
 *Represents a bank customer, which is a specific type of person.
 */
public class Customer extends Person {
    private int id;

    /**
     * Constructs a Customer with a first name, last name, and customer ID.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param id the unique customer ID
     */
    public Customer(String firstName, String lastName, int id) {
        super(firstName, lastName);  // Call to the parent class constructor
        this.id = id;
    }

    // Getter and Setter for customer ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Overriding the getFullName method to include customer-specific info (optional)
    @Override
    public String getFullName() {
        return "Customer: " + super.getFullName() + " (ID: " + id + ")";
    }
}
