import org.junit.jupiter.api.extension.ExtendWith;
import org.pastalab.fray.junit.junit5.FrayTestExtension;
import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@ExtendWith(FrayTestExtension.class)
public class LockTest {

    @ConcurrencyTest
    public void testConcurrentWriter() throws InterruptedException {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        AtomicInteger integer = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            lock1.lock();
            lock2.lock();
            integer.incrementAndGet();
            lock2.unlock();
            lock1.unlock();
        });
        Thread t2 = new Thread(() -> {
            lock2.lock();
            lock1.lock();
            integer.incrementAndGet();
            lock1.unlock();
            lock2.unlock();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
