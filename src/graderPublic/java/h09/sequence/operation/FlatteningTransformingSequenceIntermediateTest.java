package h09.sequence.operation;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class FlatteningTransformingSequenceIntermediateTest {

    @Test
    void testFields() {
        FlatteningTransformingSequenceGenericAssertions.checkFields(false);
    }

    @Test
    void testConstructor() {
        FlatteningTransformingSequenceGenericAssertions.checkConstructor(false);
    }
}
