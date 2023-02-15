package h09.sequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArraySequenceAdvancedTest {

    @Test
    @SuppressWarnings("rawtypes")
    void testIteratorAdvanced() {
        final int size = 100;
        final Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i * 55;
        }
        final ArraySequence sequence = new ArraySequence(array);
        final var iterator = sequence.iterator();
        for (int i = 0; i < size; i++) {
            Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
            Assertions.assertEquals(i * 55, iterator.next(), "Iterator should return the correct element");
        }
        Assertions.assertFalse(iterator.hasNext(), "Iterator should have exactly " + size + " elements, but there were more!");
    }
}
