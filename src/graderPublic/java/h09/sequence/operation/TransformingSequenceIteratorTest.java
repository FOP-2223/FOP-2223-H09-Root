package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class TransformingSequenceIteratorTest {

    /**
     * Tests the iterator of the TransformingSequence class.
     *
     * @param checkEndState Whether to make sure that the iterator is exhausted after the sequence is over
     */
    public static void testIteratorFunction(boolean checkEndState) {
        final Constructor<TransformingSequence> constructor = Assertions.assertDoesNotThrow(() ->
                TransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class),
            "TransformingSequence does not have a correct constructor");
        final Sequence<Integer> og = Sequence.of(1, 2, 3, 4, 5);
        final TransformingSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(og, (Function<Integer, Double>) x -> x * 10.5),
            "TransformingSequence constructor should not throw an exception");
        final Iterator it = sequence.iterator();
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(it.hasNext(), "TransformingSequence should have 5 elements");
            Assertions.assertEquals((i + 1) * 10.5, it.next(), "TransformingSequence should have elements 10, 20, 30, 40, 50");
        }
        if (checkEndState) {
            Assertions.assertFalse(it.hasNext(), "TransformingSequence iterator hasNext should be false after sequence is over");
        }
    }
}
