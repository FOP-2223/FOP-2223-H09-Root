package h09.basic;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class IntegerFactoryTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignature(
            IntegerFactory.class, BasicFactory.class, Integer.class);    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 2000})
    void testSimple(final int step) {
        final IntegerFactory factory = new IntegerFactory(1, step);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(1 + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 2000})
    void testNegativeStart(final int step) {
        final int start = -192;
        final IntegerFactory factory = new IntegerFactory(start, step);
        for (int i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -5, -2000})
    void testNegativeStep(final int step) {
        final int start = 100;
        final IntegerFactory factory = new IntegerFactory(start, step);
        for (int i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, -5, 5, -2000, 2000})
    void testAll(final int step) {
        final int start = -7;
        final IntegerFactory factory = new IntegerFactory(start, step);
        for (int i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }
}
