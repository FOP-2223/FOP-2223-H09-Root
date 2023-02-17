package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.function.Predicate;

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

    @Test
    @SuppressWarnings("rawtypes")
    void testIteratorGenerics() {
        final TypeVariable<Class<FilteringSequence>> genericT = FilteringSequence.class.getTypeParameters()[0];
        final Constructor<FilteringSequence> constructor = Assertions.assertDoesNotThrow(() ->
                FilteringSequence.class.getDeclaredConstructor(Sequence.class, Predicate.class),
            "FilteringSequence does not have constructor with parameters of raw types Sequence and Predicate");
        final FilteringSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(Sequence.of(List.of()), (Predicate) s -> true),
            "Failed to invoke FilteringSequence constructor");
        final Class<?> localIteratorClass =
            SequenceIteratorAssertions.checkIteratorMethod(genericT, genericT, FilteringSequence.class, sequence);
        SequenceIteratorAssertions.checkIteratorField(genericT, FilteringSequence.class, localIteratorClass, 2);
    }
}
