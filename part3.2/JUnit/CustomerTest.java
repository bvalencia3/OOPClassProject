import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        Checking checking = new Checking(1000.0);
        Savings savings = new Savings(2000.0);
        Credit credit = new Credit(500.0, 3000.0);
        customer = new Customer("John Doe", "12345", checking, savings, credit);
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", customer.getName(), "Customer name should be John Doe");
    }

    @Test
    public void testGetCheckingAccountBalance() {
        assertEquals(1000.0, customer.getCheckingAccount().getBalance(), "Checking account balance should be 1000.0");
    }

    @Test
    public void testGetSavingsAccountBalance() {
        assertEquals(2000.0, customer.getSavingsAccount().getBalance(), "Savings account balance should be 2000.0");
    }

    @Test
    public void testGetCreditAccountBalance() {
        assertEquals(500.0, customer.getCreditAccount().getBalance(), "Credit account balance should be 500.0");
    }

    @Test
    public void testGetCreditLimit() {
        assertEquals(3000.0, customer.getCreditLimit(), "Credit account limit should be 3000.0");
    }

}
