package BankProject.src.project;

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
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getters and setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }
    

    // Method to display person's full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}


