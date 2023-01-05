package h09.sequence;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Map;

@TestForSubmission
public final class ArraySequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            ArraySequence.class, Sequence.class, Map.of("T", Object.class));
    }

    @Test
    void testIteratorBasic() {
        final ArraySequence<Integer> sequence = new ArraySequence<>(new Integer[]{1, 2, 3});
        final var iterator = sequence.iterator();
        Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
        Assertions.assertEquals(1, iterator.next(), "Iterator should return the first element");
        Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
        Assertions.assertEquals(2, iterator.next(), "Iterator should return the second element");
        Assertions.assertTrue(iterator.hasNext(), "Iterator should have a next element");
        Assertions.assertEquals(3, iterator.next(), "Iterator should return the third element");
        Assertions.assertFalse(iterator.hasNext(), "Iterator should not have a next element");
    }
}
