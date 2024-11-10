import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * Unit test class for the Checking account's functionality.
 */
public class CheckingTest {

    private Checking checkingAccount;

    /**
     * This method runs only once at the beginning.
     */
    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("Starting CheckingTest");
    }

    /**
     * Initializes Checking balance with a balance default
     */
    @BeforeEach
    public void setUp() {
        checkingAccount = new Checking(100.0);
    }

    /**
     * Tests the deposit method in the Checking class.
     */
    @Test
    public void testDeposit() {
        double depositAmount = 50.0;
        double expectedBalance = 150.0;
        checkingAccount.deposit(depositAmount);
        assertEquals(expectedBalance, checkingAccount.getBalance(),
                "The balance should be 150.0 after depositing 50.0.");
    }

    /**
     * Cleans up after each test case.
     */
    @AfterEach
    public void tearDown() {
        System.out.println("Cleaning up after each test case...");
    }

    /**
     * Cleans up any resources that were initialized in the @BeforeAll method
     */
    @AfterAll
    public static void tearDownAfterAll() {
        System.out.println("Completed CheckingTest");
    }
}