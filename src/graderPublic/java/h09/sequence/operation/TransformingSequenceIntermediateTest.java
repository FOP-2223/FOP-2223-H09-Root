package h09.sequence.operation;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class TransformingSequenceIntermediateTest {

    @Test
    void testSignature() {
        TransformingSequenceGenericAssertions.checkSignature(false);
    }

    @Test
    void testConstructor() {
        TransformingSequenceGenericAssertions.checkConstructor(false);
    }
}
