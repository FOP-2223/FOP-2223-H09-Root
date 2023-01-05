package h09.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class IntegerBasicBinaryOperationsTest {

    @Test
    void testSignature() {
        BasicBinaryOperationsSignatureTest.testSignature(IntegerBasicBinaryOperations.class, Integer.class, Integer.class);
    }

    @Test
    void testAdd() {
        final BasicBinaryOperations<Integer, Integer> operations = new IntegerBasicBinaryOperations();
        for (int i = -20; i < 20; i++) {
            for (int j = -20; j < 20; j++) {
                Assertions.assertEquals(i + j, operations.add(i, j),
                    i + " + " + j + " should be " + (i + j));
            }
        }
    }

    @Test
    void testMul() {
        final BasicBinaryOperations<Integer, Integer> operations = new IntegerBasicBinaryOperations();
        for (int i = -20; i < 20; i++) {
            for (int j = -20; j < 20; j++) {
                Assertions.assertEquals(i * j, operations.mul(i, j),
                    i + " * " + j + " should be " + (i * j));
            }
        }
    }
}
