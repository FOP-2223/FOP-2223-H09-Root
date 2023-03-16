package h09.sequence.operation;

import h09.FieldExtensions;
import h09.sequence.Sequence;
import h09.variance.Variance;
import h09.variance.VarianceNode;
import h09.variance.VarianceTestExtensions;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("rawtypes")
public final class TransformingSequenceGenericAssertions {

    /**
     * Checks the signature of the TransformingSequence class.
     *
     * @param strict Whether to check for strict variance
     */
    public static void checkSignature(final boolean strict) {
        final TypeVariable<Class<TransformingSequence>>[] typeParameters = TransformingSequence.class.getTypeParameters();
        final TypeVariable<Class<TransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<TransformingSequence>> genericR = typeParameters[1];
        final Field[] fields = TransformingSequence.class.getDeclaredFields();
        Assertions.assertEquals(2, fields.length, "TransformingSequence should have two fields");
        FieldExtensions.assertPrivateFinal("TransformingSequence", fields);

        final Field sequenceField = Stream.of(fields).filter(field -> field.getType().equals(Sequence.class)).findAny()
            .orElseThrow(() -> new AssertionError("TransformingSequence should have a field who's raw type is type Sequence"));
        final Field functionField = Stream.of(fields).filter(field -> field.getType().equals(Function.class)).findAny()
            .orElseThrow(() -> new AssertionError("TransformingSequence should have a field who's raw type is type Sequence"));

        VarianceTestExtensions.assertVariance(sequenceField.getGenericType(), strict,
            new VarianceNode(genericT, Variance.COVARIANT));
        VarianceTestExtensions.assertVariance(functionField.getGenericType(), strict,
            new VarianceNode(genericT, Variance.CONTRAVARIANT), new VarianceNode(genericR, Variance.COVARIANT));
    }

    static void checkConstructor(final boolean strict) {
        final TypeVariable<Class<TransformingSequence>>[] typeParameters = TransformingSequence.class.getTypeParameters();
        final TypeVariable<Class<TransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<TransformingSequence>> genericR = typeParameters[1];
        final Constructor<TransformingSequence> constructor = Assertions.assertDoesNotThrow(() ->
            TransformingSequence.class.getDeclaredConstructor(Sequence.class, Function.class));
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "TransformingSequence's constructor should be public");
        final Parameter[] parameters = constructor.getParameters();
        final Parameter sequenceParameter = parameters[0];
        final Parameter functionParameter = parameters[1];

        VarianceTestExtensions.assertVariance(sequenceParameter.getParameterizedType(), strict,
            new VarianceNode(genericT, Variance.COVARIANT));
        VarianceTestExtensions.assertVariance(functionParameter.getParameterizedType(), strict,
            new VarianceNode(genericT, Variance.CONTRAVARIANT), new VarianceNode(genericR, Variance.COVARIANT));
    }
}
