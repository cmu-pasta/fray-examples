import org.pastalab.fray.core.scheduler.POSScheduler;
import org.pastalab.fray.junit.annotations.ConcurrencyTest;

import java.util.concurrent.atomic.AtomicInteger;

public class TestExample extends Thread {
    static Object o = new Object();
    static AtomicInteger a = new AtomicInteger();
    static volatile int b;
    public void run() {
        int x = a.getAndIncrement();
        synchronized(o) { // monitorenter
            if (x == 0) {
                try { o.wait(); } catch (InterruptedException ignore) { }
            } else {
                o.notify();
            }
        } // monitorexit
        b = x;
    }


    @ConcurrencyTest(
            iteration = 1000
    )
    public void test() throws Exception {
        a = new AtomicInteger();
        b = 0;
        TestExample[] threads = {new TestExample(), new TestExample()};
        for (var thread : threads) thread.start();
        for (var thread : threads) thread.join();
    }
}