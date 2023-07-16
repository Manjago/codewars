import java.math.BigInteger;

public class SimpleTest {
    public static void assertEquals(BigInteger expected, BigInteger actual) {
        if (!expected.equals(actual)) {
            throw new IllegalStateException("Expected " + expected + ", actual " + actual);
        }
    }
}
