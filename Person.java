package project;

/**
 * an abstract class representing a person.
 * @author Gian Hernandez
 */
public abstract class Person {
    private String firstName;
    private String lastName;

    /**
     * Constructor for a Person with a first and last name.
     * @param name1 first name
     * @param name2 last name
     */
    public Person(String name1, String name2) {
        this.firstName = name1;
        this.lastName = name2;
    }

    // getters and setters
    public void setFirstName(String name1) {
        this.firstName = name1;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String name2) {
        this.lastName = name2;
    }

    public String getLastName() {
        return this.lastName;
    }
}

/**
 * A subclass of Person, representing a Customer
 * @author Gian Hernandez
 */
public class Customer extends Person {
    private int id;

    /**
     * A default constructor for the Customer class
     */
    public Customer() {
        super("","");
        this.id = -1;
    }

    /**
     * A constructor for the Customer class which takes 3 parameters/
     * @param name1 value to be assigned to firstName
     * @param name2 value to be assigned to lastName
     * @param id value to be assigned to id
     */
    public Customer(String name1, String name2, int id) {
        super(name1,name2);
        this.id = id;
    }

    // getter and setter
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    //public boolean isCustomer() {}
}
