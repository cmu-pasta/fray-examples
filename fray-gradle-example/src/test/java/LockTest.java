import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

public class LockTest {
    public static class ConcurrentWriter {
        int counter = 0;
        public void write(int count) {
            synchronized (this) {
                counter = count;
            }
        }
        public void increase() {
            synchronized (this) {
                counter++;
            }
        }
    }

    @ConcurrencyTest
    public void testConcurrentWriter() throws InterruptedException {
        ConcurrentWriter writer = new ConcurrentWriter();
        Thread t1 = new Thread(() -> {
            writer.write(1);
        });
        Thread t2 = new Thread(() -> {
            writer.increase();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
