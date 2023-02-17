package h09.sequence.operation;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class FlattingTransformingSequenceAdvancedGenericsTest {

    @Test
    void testFields() {
        FlatteningTransformingSequenceGenericAssertions.checkFields(true);
    }

    @Test
    void testConstructor() {
        FlatteningTransformingSequenceGenericAssertions.checkConstructor(true);
    }
}
