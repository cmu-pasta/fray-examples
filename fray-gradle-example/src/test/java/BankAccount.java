import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    public AtomicInteger balance = new AtomicInteger(1000);
    public void withdraw(int amount) {
        // Check if there is enough balance
        if (balance.get() >= amount) {
            // Deduct the amount
            balance.set(balance.get() - amount);
        }
    }
}
