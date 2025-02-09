import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pastalab.fray.junit.junit5.FrayTestExtension;
import org.pastalab.fray.junit.junit5.annotations.ConcurrencyTest;

@ExtendWith(FrayTestExtension.class)
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
