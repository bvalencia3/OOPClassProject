import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

@Suite
@SelectClasses({
        // Add classes with tests
        AccountTest.class
})
public class AllTestsSuite {

    /**
     * Runs once before all tests in the suite.
     */
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("Starting test suite setup...");
    }

    /**
     * Runs once after all tests in the suite.
     */
    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("Test suite complete");
}
