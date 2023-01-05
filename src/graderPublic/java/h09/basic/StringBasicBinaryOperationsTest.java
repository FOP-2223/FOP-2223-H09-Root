package h09.basic;

import h09.RandomExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;

@TestForSubmission
public class StringBasicBinaryOperationsTest {

    @Test
    void testSignature() {
        BasicBinaryOperationsSignatureTest.testSignature(StringBasicBinaryOperations.class, String.class, Integer.class);
    }

    @Test
    void testAddNormalCase() {
        final BasicBinaryOperations<String, Integer> operations = new StringBasicBinaryOperations();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final String a = RandomExtensions.generateString(random, i);
            final String b = RandomExtensions.generateString(random, i + 1);
            Assertions.assertEquals(a + b, operations.add(a, b),
                a + " + " + b + " should be " + (a + b));
        }
    }

    @Test
    void testAddEmpty() {
        final BasicBinaryOperations<String, Integer> operations = new StringBasicBinaryOperations();
        Assertions.assertEquals("", operations.add("", ""),
            "\"\" + \"\" should be \"\"");
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final String a = RandomExtensions.generateString(random, i);
            Assertions.assertEquals(a, operations.add(a, ""),
                a + " + \"\" should be " + a);
            Assertions.assertEquals(a, operations.add("", a),
                "\"\" + " + a + " should be " + a);
        }
    }

    @Test
    void testMulNonNegative() {
        final BasicBinaryOperations<String, Integer> operations = new StringBasicBinaryOperations();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final String a = RandomExtensions.generateString(random, i);
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(a.repeat(j), operations.mul(a, j),
                    a + " * " + j + " should be " + a.repeat(j));
            }
        }
    }

    @Test
    void testMulNegative() {
        final BasicBinaryOperations<String, Integer> operations = new StringBasicBinaryOperations();
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final String a = RandomExtensions.generateString(random, i);
            for (int j = -10; j < 0; j++) {
                Assertions.assertEquals("", operations.mul(a, j),
                    a + " * " + j + " should be \"\"");
            }
        }
    }
}
