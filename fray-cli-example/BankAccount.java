import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {

    public AtomicInteger balance = new AtomicInteger(1000);
    public void withdraw(int amount) {
        if (balance.get() >= amount) {
            balance.set(balance.get() - amount);
        }
    }
    

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread t1 = new Thread(() -> {
            account.withdraw(500);
            assert(account.balance.get() > 0);
        }, "withdraw-1");
        Thread t2 = new Thread(() -> {
            account.withdraw(700);
            assert(account.balance.get() > 0);
        }, "withdraw-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}