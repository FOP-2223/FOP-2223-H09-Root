package h09.sequence.operation;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class TransformingSequenceAdvancedTest {

    @Test
    void testSignature() {
        TransformingSequenceGenericAssertions.checkSignature(true);
    }

    @Test
    void testConstructor() {
        TransformingSequenceGenericAssertions.checkConstructor(true);
    }

    // TODO: Other strict tests?
}
