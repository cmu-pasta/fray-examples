import org.junit.jupiter.api.Test;
import org.pastalab.fray.junit.annotations.ConcurrencyTest;

public class SimpleTest {

    @Test
    public void test() {
        assert(false);
    }

    @ConcurrencyTest
    public void concurrencyTest() {
        assert(false);
    }

}
