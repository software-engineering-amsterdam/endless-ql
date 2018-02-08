import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JavaTestTest {
    @Test
    public void helloWorldReturnsHelloWorld() {
        assertEquals(JavaTest.helloWorld(), "Hello World!");
    }
}
