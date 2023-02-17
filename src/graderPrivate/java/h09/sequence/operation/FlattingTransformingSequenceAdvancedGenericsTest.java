package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import h09.variance.Variance;
import h09.variance.VarianceNode;
import h09.variance.VarianceTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

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

    @Test
    @SuppressWarnings("rawtypes")
    void testIteratorGenerics() {
        final TypeVariable<Class<FlatteningTransformingSequence>>[] typeParameters = FlatteningTransformingSequence.class.getTypeParameters();
        final TypeVariable<Class<FlatteningTransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<FlatteningTransformingSequence>> genericR = typeParameters[1];
        final Constructor<FlatteningTransformingSequence> constructor = Assertions.assertDoesNotThrow(() ->
                FlatteningTransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class),
            "FlatteningTransformingSequence does not have constructor with parameters of raw types Sequence and Function");
        final FlatteningTransformingSequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(Sequence.of(List.of()), Function.identity()),
            "Failed to invoke FlatteningTransformingSequence constructor");
        final Class<?> localIteratorClass =
            SequenceIteratorAssertions.checkIteratorMethod(genericT, genericR, FlatteningTransformingSequence.class, sequence);
        SequenceIteratorAssertions.checkIteratorField(genericT, FlatteningTransformingSequence.class, localIteratorClass, 2);

        final Field[] fields = localIteratorClass.getDeclaredFields();

        final Field iteratorField = Arrays.stream(fields)
            .filter(f -> Objects.equals(f.getType(), Iterator.class))
            .filter(f -> Objects.equals(f.getName(), "currentIterator"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("The FlatteningTransformingSequence iterator should have a field" +
                " with the name currentIterator who's raw type is Iterator"));

        Assertions.assertTrue(Modifier.isPrivate(iteratorField.getModifiers()),
            "The iterator field in the FlatteningTransformingSequence iterator should be private");

        VarianceTestExtensions.assertStrictVariance(iteratorField.getGenericType(),
            new VarianceNode(genericR, Variance.COVARIANT));
    }
}
