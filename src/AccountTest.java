import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for the Account class.
 * Tests core account operations, such as deposit, withdrawal, balance
 * retrieval,
 * and transfer functionality in a Checking account.
 */
public class AccountTest {

    private Account account;

    /**
     * Sets up resources required for the AccountTest class.
     * This method is called once before all test methods.
     */
    @BeforeAll
    public static void setUpBeforeClass() {
        System.out.println("Starting AccountTest class setup...");
        // Class-level setup, if needed
    }

    /**
     * Cleans up resources used by the AccountTest class.
     * This method is called once after all test methods.
     */
    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("Completed AccountTest class teardown.");
        // Class-level teardown, if needed
    }

    /**
     * Initializes a new Account instance before each test.
     * Sets the initial balance to 1000 for consistent test conditions.
     */
    @BeforeEach
    public void setUp() {
        account = new Checking(1000.00); // Initial balance of 1000
        System.out.println("Setting up for a new test with initial balance: " + account.getBalance());
    }

    /**
     * Cleans up after each test by resetting the account reference.
     * Ensures each test runs in isolation.
     */
    @AfterEach
    public void tearDown() {
        System.out.println("Test completed. Resetting account.");
        account = null; // Resetting account reference to ensure each test is isolated
    }

    /**
     * Tests the deposit method to ensure the balance is correctly increased by the
     * deposited amount.
     */
    @Test
    public void testDeposit() {
        account.deposit(500.00);
        assertEquals(1500.00, account.getBalance(), "Balance should be 1500 after depositing 500");
    }

    /**
     * Tests the withdraw method with a sufficient balance.
     * Verifies that the withdrawal is successful and the balance is reduced
     * accordingly.
     */
    @Test
    public void testWithdrawWithSufficientBalance() {
        boolean result = account.withdraw(200.00);
        assertTrue(result, "Withdraw should succeed with sufficient balance");
        assertEquals(800.00, account.getBalance(), "Balance should be 800 after withdrawing 200");
    }

    /**
     * Tests the withdraw method with an insufficient balance.
     * Ensures that the withdrawal fails and the balance remains unchanged.
     */
    @Test
    public void testWithdrawWithInsufficientBalance() {
        boolean result = account.withdraw(1500.00);
        assertFalse(result, "Withdraw should fail with insufficient balance");
        assertEquals(1000.00, account.getBalance(), "Balance should remain 1000 when withdrawal fails");
    }

    /**
     * Tests the getBalance method to confirm it returns the correct balance.
     */
    @Test
    public void testGetBalance() {
        assertEquals(1000.00, account.getBalance(), "Initial balance should be 1000");
    }

    /**
     * Tests the transfer method by transferring funds to a target account.
     * Verifies that the transfer is successful and the balances of both accounts
     * are updated correctly.
     */
    @Test
    public void testTransfer() {
        Account targetAccount = new Checking(500.00); // Another account with initial balance 500
        boolean result = account.transfer(targetAccount, 300.00);
        assertTrue(result, "Transfer should succeed with sufficient balance");
        assertEquals(700.00, account.getBalance(), "Source balance should be 700 after transferring 300");
        assertEquals(800.00, targetAccount.getBalance(), "Target balance should be 800 after receiving 300");
    }
}
