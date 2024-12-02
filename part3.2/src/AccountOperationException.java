
/**
 * A custom exception for general account operation errors.
 */
public class AccountOperationException extends RuntimeException {

    /**
     * Constructs an AccountOperationException with a message.
     */
    public AccountOperationException() {
        super("An error occurred during the account operation.");
    }

    /**
     * creates an AccountOperationException with a custom message.
     * 
     * @param message the detailed error message
     */
    public AccountOperationException(String message) {
        super(message);
    }
}
