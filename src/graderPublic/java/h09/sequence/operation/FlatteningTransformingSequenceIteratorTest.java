package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.PrimitiveSequence;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.function.Function;

@SuppressWarnings({"rawtypes", "unchecked"})
public class FlatteningTransformingSequenceIteratorTest {

    /**
     * Tests the iterator of the FlatteningTransformingSequence class.
     *
     * @param checkEndState Whether to make sure that the iterator is exhausted after the sequence is over
     */
    static void testIteratorFunction(boolean checkEndState) {
        final Constructor<FlatteningTransformingSequence> constructorF = Assertions.assertDoesNotThrow(() ->
                FlatteningTransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class),
            "FlatteningTransformingSequence does not have a correct constructor");
        final Constructor<TransformingSequence> constructorT = Assertions.assertDoesNotThrow(() ->
                TransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class),
            "TransformingSequence does not have a correct constructor");
        final Sequence<String> ogSeq = Sequence.of("1", "23", "456");
        final FlatteningTransformingSequence charSeq = InvokeAssertions.assertDoesNotThrow(() ->
                constructorF.newInstance(ogSeq,
                    (Function<String, Sequence<Character>>) s -> PrimitiveSequence.of(s.toCharArray())),
            "FlatteningTransformingSequence constructor should not throw an exception");
        final TransformingSequence seq = InvokeAssertions.assertDoesNotThrow(() ->
                constructorT.newInstance(charSeq, (Function<Character, Integer>) Character::getNumericValue),
            "TransformingSequence constructor should not throw an exception");
        Iterator<Integer> it = seq.iterator();
        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(it.hasNext(),
                "Resulting sequence should have 6 elements! Has: " + i + " element" + (i == 1 ? "" : "s"));
            Assertions.assertEquals(i + 1, it.next(),
                "Resulting sequence should be: {1, 2, 3, 4, 5, 6}! Wrong at index: " + i);
        }
        if (checkEndState) {
            Assertions.assertFalse(it.hasNext(),
                "FlatteningTransformingSequence iterator hasNext should be false after sequence is over");
        }
    }
}
