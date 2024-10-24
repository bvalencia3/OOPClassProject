
public class Checking extends Account {
    public Checking(double balance) {
        super(balance);
    }

    @Override
    public String toString() {
        return "Checking Account - " + super.toString();
    }
}
