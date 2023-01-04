package h09.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class DoubleFactoryTest {

    @Test
    void testSignature() {
        BasicFactorySignatureTest.testSignature(DoubleFactory.class, Double.class);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1, 3.5, 5.75, 2000.5})
    void testSimple(final double step) {
        final BasicFactory<Double> factory = new DoubleFactory(1, step);
        for (double i = 0; i < 500; i++) {
            Assertions.assertEquals(1 + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1, 3.5, 5.75, 2000.5})
    void testNegativeStart(final double step) {
        final double start = -143.5;
        final BasicFactory<Double> factory = new DoubleFactory(start, step);
        for (double i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.5, -2.25, -5.25, -1500.5})
    void testNegativeStep(final double step) {
        final double start = 100;
        final BasicFactory<Double> factory = new DoubleFactory(start, step);
        for (double i = 0; i < 200; i++) {
            Assertions.assertEquals(start + i * step, factory.create(),
                "Failed for step = " + step + " and i = " + i);
        }
    }
}
