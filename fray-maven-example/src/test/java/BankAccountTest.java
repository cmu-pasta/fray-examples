import org.junit.jupiter.api.Test;
import org.pastalab.fray.core.scheduler.POSScheduler;
import org.pastalab.fray.junit.annotations.ConcurrencyTest;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountTest {
    public static class BankAccount {
        private AtomicInteger balance = new AtomicInteger();

        public BankAccount(int initialBalance) {
            this.balance.set(initialBalance);
        }

        // Transfer money from this account to another account
        public void transfer(BankAccount target, int amount) {
            if (this.balance.get() >= amount) {
                this.balance.set(this.balance.get() - amount);
                target.balance.set(target.balance.get() + amount);
            } else {
                System.out.println("Insufficient funds to transfer " + amount + " from " + this);
            }
        }
    }

    @Test
    public void testBankAccountHammer() {
        for (int i = 0; i < 10000; i++) {
            testBankAccount();
        }
    }


    @ConcurrencyTest(
            scheduler = POSScheduler.class,
            iteration = 1000
    )
    public void testBankAccount() {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);
        // Thread 1: Transfer 500 from account1 to account2
        Thread thread1 = new Thread(() -> {
            account1.transfer(account2, 500);
        });
        // Thread 2: Transfer 700 from account1 to account2
        Thread thread2 = new Thread(() -> {
            account1.transfer(account2, 700);
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(account1.balance.get() >= 0);
    }
}

