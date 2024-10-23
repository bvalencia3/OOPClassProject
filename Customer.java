package project;

/**
 * A subclass of Person, representing a Customer
 * @author Gian Hernandez
 *Represents a bank customer, which is a specific type of person.
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;

    /**
     * Constructs a Customer with a first name, last name, and customer ID.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param id the unique customer ID
     */
    public Customer() {
        this.id = -1;  // Call to the parent class constructor
        this.firstName = "";
        this.lastName = "";
    }

    public Customer(String name1, String name2) {
        this.id = -1;
        this.firstName = name1;
        this.lastName = name2;
    }

    // Getter and Setter for customer ID
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }
}