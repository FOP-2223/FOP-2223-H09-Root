package h09.sequence.operation;

import h09.InvokeAssertions;
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
import java.util.function.Predicate;
import java.util.stream.Stream;

@TestForSubmission
public class FilteringSequenceOfTest {

    @Test
    void testOf() {
        final Method ofMethod = SequenceOfAssertions.checkBasics(FilteringSequence.class, Predicate.class,
            method -> method.getTypeParameters()[0], method -> method.getTypeParameters()[0]);
        Assertions.assertArrayEquals(
            new String[]{"T"},
            Stream.of(ofMethod.getTypeParameters()).map(TypeVariable::getName).toArray(String[]::new),
            "Method FilteringSequence.of should have one type parameter T"
        );
        final TypeVariable<?> genericT = ofMethod.getTypeParameters()[0];
        // check parameter type

        if (!(ofMethod.getGenericParameterTypes()[0] instanceof ParameterizedType parameterizedPredicate)) {
            throw new AssertionFailedError("Method FilteringSequence.of should parameterize parameter type Predicate");
        }

        VarianceTestExtensions.assertStrictVariance(parameterizedPredicate, new VarianceNode(genericT, Variance.CONTRAVARIANT));

        final Object result = InvokeAssertions.assertDoesNotThrow(() -> ofMethod.invoke(null, (Predicate<Object>) Objects::nonNull),
            "Method FilteringSequence.of should not throw an exception when called with a non-null Predicate");

        Assertions.assertNotNull(result, "Method FilteringSequence.of should return a non-null value");
    }
}
