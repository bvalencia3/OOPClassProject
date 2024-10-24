
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * The Person class with a name and ID
 */
public class Person {
    private String name;
    private String id;

    /**
     * Creates a person object with a specific name and ID.
     * 
     * @param name the name of the person
     * @param id the ID of the person
     */
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the name of the person
     * 
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the person
     * 
     * @return the ID of the person
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a string of the Person object
     * 
     * @return the name and ID of the person
     */
    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id;
    }
}

