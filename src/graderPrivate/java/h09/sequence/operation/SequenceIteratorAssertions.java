package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.variance.Variance;
import h09.variance.VarianceNode;
import h09.variance.VarianceTestExtensions;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SequenceIteratorAssertions {

    public static Class<?> checkIteratorMethod(final TypeVariable<?> outputGenericType,
                                               final Class<?> enclosingClass,
                                               final Object instance) {
        final Method method = Assertions.assertDoesNotThrow(() ->
                enclosingClass.getDeclaredMethod("iterator"),
            enclosingClass.getSimpleName() + " should have a method named iterator");
        Assertions.assertEquals(0, method.getTypeParameters().length,
            enclosingClass.getSimpleName() + ".iterator should not have any type parameters");
        final String genericIteratorName = Iterator.class.getTypeName() + "<" + outputGenericType.getName() + ">";
        Assertions.assertEquals(Iterator.class, method.getReturnType(),
            enclosingClass.getSimpleName() + ".iterator should return an Iterator");
        if (method.getGenericReturnType() instanceof ParameterizedType genericIterator) {
            Assertions.assertEquals(outputGenericType, genericIterator.getActualTypeArguments()[0],
                enclosingClass.getSimpleName() + ".iterator should return an " + genericIteratorName
                    + " (the same type as the sequence's type parameter)");
        } else {
            Assertions.fail(enclosingClass.getSimpleName() + ".iterator should return an " + genericIteratorName
                + " (the same type as the sequence's type parameter)"
                + " but instead returned a raw type Iterator");
        }

        return InvokeAssertions.assertDoesNotThrow(() ->
            method.invoke(instance), "Failed to invoke " + enclosingClass.getSimpleName() + ".iterator").getClass();
    }

    public static void checkIteratorField(
        final TypeVariable<?> genericT,
        final Class<?> enclosingClass,
        final Class<?> localIteratorClass,
        final int expectedFieldCount
    ) {
        final Field[] fields = localIteratorClass.getDeclaredFields();
        // one extra field for "this$0" (the outer class)
        Assertions.assertEquals(expectedFieldCount, fields.length - 1, "Iterator should have " + expectedFieldCount + " fields");

        final Field iteratorField = Arrays.stream(fields)
            .filter(f -> Objects.equals(f.getType(), Iterator.class))
            .filter(f -> Objects.equals(f.getName(), "iterator"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("The " + enclosingClass.getSimpleName() + " iterator should have a field" +
                " with the name iterator who's raw type is Iterator"));

        Assertions.assertTrue(Modifier.isFinal(iteratorField.getModifiers()),
            "The iterator field in the " + enclosingClass.getSimpleName() + " iterator should be final");
        /*
         * To check if the field is private:
         *
         * Assertions.assertTrue(Modifier.isPrivate(iteratorField.getModifiers()),
         *             "The iterator field in the " + enclosingClass.getSimpleName() + " iterator should be private");
         */

        VarianceTestExtensions.assertStrictVariance(iteratorField.getGenericType(),
            new VarianceNode(genericT, Variance.COVARIANT));
    }
}
