package h09.sequence.operation;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class FilteringSequenceAdvancedTest {

    @Test
    void testFields() {
        FilteringSequenceGenericAssertions.checkFields(true);
    }

    @Test
    void testConstructor() {
        FilteringSequenceGenericAssertions.checkConstructor(true);
    }

    // TODO: Other strict tests?
}
