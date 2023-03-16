package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import h09.variance.Variance;
import h09.variance.VarianceNode;
import h09.variance.VarianceTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

@TestForSubmission
public class FlatteningTransformingSequenceOfTest {

    @Test
    void testOf() {
        final Method ofMethod = SequenceOfAssertions.checkBasics(FlatteningTransformingSequence.class, Function.class,
            method -> method.getTypeParameters()[0], method -> method.getTypeParameters()[1]);
        Assertions.assertArrayEquals(
            new String[]{"T", "R"},
            Stream.of(ofMethod.getTypeParameters()).map(TypeVariable::getName).toArray(String[]::new),
            "Method FlatteningTransformingSequence.of should have two type parameters T and R"
        );
        final TypeVariable<?> genericT = ofMethod.getTypeParameters()[0];
        final TypeVariable<?> genericR = ofMethod.getTypeParameters()[1];
        // check parameter type

        if (!(ofMethod.getGenericParameterTypes()[0] instanceof ParameterizedType parameterizedPredicate)) {
            throw new AssertionFailedError(
                "Method FlatteningTransformingSequence.of should parameterize parameter type Function");
        }

        VarianceTestExtensions.assertStrictVariance(parameterizedPredicate,
            new VarianceNode(genericT, Variance.CONTRAVARIANT),
            new VarianceNode(Sequence.class, Variance.COVARIANT, new VarianceNode(genericR, Variance.COVARIANT)));

        final Object result = InvokeAssertions.assertDoesNotThrow(
            () -> ofMethod.invoke(null, (Function<Object, Object>) Objects::nonNull),
            "Method FlatteningTransformingSequence.of should not throw an exception when called with a non-null Function");

        Assertions.assertNotNull(result, "Method FlatteningTransformingSequence.of should return a non-null value");
    }
}
