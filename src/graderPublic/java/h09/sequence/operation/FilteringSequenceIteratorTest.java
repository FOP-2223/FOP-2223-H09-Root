package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("rawtypes")
public class FilteringSequenceIteratorTest {

    /**
     * Tests the iterator of the FilteringSequence class.
     *
     * @param checkEndState Whether to make sure that the iterator is exhausted after the sequence is over
     */
    public static void testIteratorFunction(boolean checkEndState) {
        final Constructor<FilteringSequence> constructor = Assertions.assertDoesNotThrow(() ->
                FilteringSequence.class.getDeclaredConstructor(Sequence.class, Predicate.class),
            "FilteringSequence does not have a correct constructor");
        final Sequence<String> og = Sequence.of(List.of("aa", "bbb", "cc", "d", "eee", "ffff", "g", "hh", "ii", "jjj"));
        final FilteringSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(og, (Predicate<String>) s -> s.length() > 2),
            "Failed to invoke FilteringSequence constructor");
        final List result = List.of("bbb", "eee", "ffff", "jjj");
        final Iterator it = sequence.iterator();
        for (final Object o : result) {
            Assertions.assertTrue(it.hasNext(), "Iterator should have a next element");
            Assertions.assertEquals(o, it.next(), "Iterator should return the correct element");
        }
        if (checkEndState) {
            Assertions.assertFalse(it.hasNext(), "FilteringSequence iterator hasNext should be false after sequence is over");
        }
    }
}
