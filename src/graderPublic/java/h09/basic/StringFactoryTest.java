package h09.basic;

import h09.RandomExtensions;
import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;
import java.util.stream.IntStream;

@TestForSubmission
public final class StringFactoryTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignature(
            StringFactory.class, BasicFactory.class, String.class);
    }

    @Test
    void testSimple() {
        final int start = 1;
        final String[] input = {"Hallo", "Welt", "!"};
        final BasicFactory<String> factory = new StringFactory(start, input);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(input[(i + start) % input.length], factory.create(),
                "Failed for start = " + start + " and i = " + i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 21, 64})
    void testComplex(final int start) {
        final Random random = new Random(start);
        final String[] input = IntStream.rangeClosed(0, 65).mapToObj(i -> RandomExtensions.generateString(random, i)).toArray(String[]::new);
        final BasicFactory<String> factory = new StringFactory(start, input);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(input[(i + start) % input.length], factory.create(),
                "Failed for start = " + start + " and i = " + i);
        }
    }
}
