package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

    @Test
    @SuppressWarnings("rawtypes")
    void testIteratorGenerics() {
        final TypeVariable<Class<TransformingSequence>>[] typeParameters = TransformingSequence.class.getTypeParameters();
        final TypeVariable<Class<TransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<TransformingSequence>> genericR = typeParameters[1];
        final Constructor<TransformingSequence> constructor = Assertions.assertDoesNotThrow(() ->
                TransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class),
            "TransformingSequence does not have constructor with parameters of raw types Sequence and Function");
        final TransformingSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(Sequence.of(List.of()), Function.identity()),
            "Failed to invoke TransformingSequence constructor");
        final Class<?> localIteratorClass =
            SequenceIteratorAssertions.checkIteratorMethod(genericR, TransformingSequence.class, sequence);
        SequenceIteratorAssertions.checkIteratorField(genericT, TransformingSequence.class, localIteratorClass, 1);
    }
}
