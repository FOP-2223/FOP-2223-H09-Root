package h09.sequence.operation;

import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@TestForSubmission
public final class FilteringSequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            FilteringSequence.class, Sequence.class, Map.of("T", Object.class));
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testIteratorBasic() {
        Sequence og = Sequence.of(List.of("aa", "bbb", "cc", "d", "eee", "ffff", "g", "hh", "ii", "jjj"));
        FilteringSequence sequence = new FilteringSequence(og, s -> s.toString().length() > 2);
        final List result = List.of("bbb", "eee", "ffff", "jjj");
        final Iterator it = sequence.iterator();
        for (Object o : result) {
            Assertions.assertTrue(it.hasNext(), "Iterator should have a next element");
            Assertions.assertEquals(o, it.next(), "Iterator should return the correct element");
        }
    }
}
