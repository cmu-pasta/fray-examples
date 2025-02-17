import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pastalab.fray.junit.junit5.FrayTestExtension;
import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

@ExtendWith(FrayTestExtension.class)
public class BankAccountTest {


    public void myBankAccountTest() throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread t1 = new Thread(() -> {
            account.withdraw(500);
            assert(account.balance.get() > 0);
        });
        Thread t2 = new Thread(() -> {
            account.withdraw(700);
            assert(account.balance.get() > 0);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


    @ConcurrencyTest(
            iterations = 1000
    )
    public void runTestUsingFray() throws InterruptedException {
        myBankAccountTest();
    }

    @Test
    public void runTestUsingJunit() throws InterruptedException {
        myBankAccountTest();
    }
}
