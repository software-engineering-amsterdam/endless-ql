import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaTestTest {
    @Test
    public void helloWorldReturnsHelloWorld() {
        assertEquals(JavaTest.helloWorld(), "Hello World!");
    }

}
