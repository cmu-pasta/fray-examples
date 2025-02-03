import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pastalab.fray.core.scheduler.PCTScheduler;
import org.pastalab.fray.core.scheduler.POSScheduler;
import org.pastalab.fray.core.scheduler.RandomScheduler;
import org.pastalab.fray.junit.junit5.FrayTestExtension;
import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(FrayTestExtension.class)
public class IntStreamTest {
    public void myTest() {
        AtomicInteger x = new AtomicInteger();
        java.util.stream.IntStream.range(0, 8).parallel().forEach((i) -> x.compareAndSet(i-1, i));
        if (x.get() == 7) {
            throw new RuntimeException("x (" + x.get() + ") is 10");
        }
    }

    @ConcurrencyTest(
            iterations = 10000,
            scheduler = POSScheduler.class
    )
    public void testWithFray() {
        myTest();
    }

    @Test
    public void testWithJunit() {
        for (int i = 0; i < 1000000; i++) {
            myTest();
        }
    }
}
