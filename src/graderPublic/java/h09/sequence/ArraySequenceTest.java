package h09.sequence;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Map;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class ArraySequenceTest {

    @Test
    void testSignature() {
        SignatureTestExtensions.testSignatureSimplePassThroughParameterization(
            ArraySequence.class, Sequence.class, Map.of("T", Object.class));
    }

    // TODO: Field, Constructor, and Method tests
}
