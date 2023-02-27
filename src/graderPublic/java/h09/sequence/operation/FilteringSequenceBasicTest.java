package h09.sequence.operation;

import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Map;

@TestForSubmission
public final class FilteringSequenceBasicTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            FilteringSequence.class, Sequence.class, Map.of("T", Object.class));
    }

    @Test
    void testIteratorBasic() {
        FilteringSequenceIteratorTest.testIteratorFunction(false);
    }
}
