package h09.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class DoubleBasicBinaryOperationsTest {

    @Test
    void testSignature() {
        BasicBinaryOperationsSignatureTest.testSignature(DoubleBasicBinaryOperations.class, Double.class, Double.class);
    }

    @Test
    void testAdd() {
        final BasicBinaryOperations<Double, Double> operations = new DoubleBasicBinaryOperations();
        for (double i = -10; i < 10; i+=0.5) {
            for (double j = -10; j < 10; j+=0.25) {
                Assertions.assertEquals(i + j, operations.add(i, j),
                    i + " + " + j + " should be " + (i + j));
            }
        }
    }

    @Test
    void testMul() {
        final BasicBinaryOperations<Double, Double> operations = new DoubleBasicBinaryOperations();
        for (double i = -10; i < 10; i+=0.5) {
            for (double j = -10; j < 10; j+=0.25) {
                Assertions.assertEquals(i * j, operations.mul(i, j),
                    i + " * " + j + " should be " + (i * j));
            }
        }
    }
}
