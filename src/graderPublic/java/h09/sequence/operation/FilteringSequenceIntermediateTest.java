package h09.sequence.operation;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public final class FilteringSequenceIntermediateTest {

    @Test
    void testFields() {
        FilteringSequenceGenericAssertions.checkFields(false);
    }

    @Test
    void testConstructor() {
        FilteringSequenceGenericAssertions.checkConstructor(false);
    }
}
