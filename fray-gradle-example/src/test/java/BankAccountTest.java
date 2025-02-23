import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pastalab.fray.junit.junit5.FrayTestExtension;
import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

@ExtendWith(FrayTestExtension.class)
public class BankAccountTest {

    public static class BankAccount {
        public int balance = 1000;
        public void withdraw(int amount) {
            if (getBalance() >= amount) {
                setBalance(getBalance() - amount);
            }
        }
        public synchronized int getBalance() {
            return balance;
        }
        public synchronized void setBalance(int balance) {
            this.balance = balance;
        }
    }

    public void myBankAccountTest() throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread t1 = new Thread(() -> {
            account.withdraw(500);
            assert(account.getBalance() > 0);
        });
        Thread t2 = new Thread(() -> {
            account.withdraw(700);
            assert(account.getBalance() > 0);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @ConcurrencyTest
    public void runTestUsingFray() throws InterruptedException {
        myBankAccountTest();
    }

    @Test
    public void runTestUsingJunit() throws InterruptedException {
        myBankAccountTest();
    }
}
