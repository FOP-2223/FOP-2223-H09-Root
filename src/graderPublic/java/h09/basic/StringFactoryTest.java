package h09.basic;

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
        BasicFactorySignatureTest.testSignature(StringFactory.class, String.class);
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
        final String[] input = IntStream.rangeClosed(0, 65).mapToObj(i -> generateString(random, i)).toArray(String[]::new);
        final BasicFactory<String> factory = new StringFactory(start, input);
        for (int i = 0; i < 500; i++) {
            Assertions.assertEquals(input[(i + start) % input.length], factory.create(),
                "Failed for start = " + start + " and i = " + i);
        }
    }

    private static String generateString(final Random random, final int num) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2 + num % 7; i++) {
            builder.append((char) random.nextInt('a', 'z'));
        }
        return builder.toString();
    }
}
