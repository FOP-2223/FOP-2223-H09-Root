package h09.sequence.operation;

import h09.InvokeAssertions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.function.Function;

public class SequenceOfAssertions {

    public static Method checkBasics(
        Class<?> enclosingClass,
        Class<?> parameterType,
        Function<Method, TypeVariable<?>> genericTExtractor,
        Function<Method, TypeVariable<?>> genericRExtractor
    ) {
        final Method ofMethod = Assertions.assertDoesNotThrow(() ->
                enclosingClass.getDeclaredMethod("of", parameterType),
            "Class " + enclosingClass.getSimpleName() + " does not have method of with parameter of type " + parameterType.getName()
        );
        Assertions.assertEquals(Function.class, ofMethod.getReturnType(),
            "Method " + enclosingClass.getSimpleName() + ".of should return a Function");

        if (!(ofMethod.getGenericReturnType() instanceof ParameterizedType parameterizedFunction)) {
            throw new AssertionFailedError("Method " + enclosingClass.getSimpleName() + ".of should parameterize return type Function");
        }
        final Type[] actualTypeArguments = parameterizedFunction.getActualTypeArguments();
        final Type functionT = actualTypeArguments[0];
        final Type functionR = actualTypeArguments[1];

        if (!(functionT instanceof ParameterizedType pFunctionT)) {
            throw new AssertionFailedError("Method " + enclosingClass.getSimpleName() + ".of does not parameterize the first type parameter of Function"
                + " (" + functionT.getTypeName() + ")");
        }
        if (!(functionR instanceof ParameterizedType pFunctionR)) {
            throw new AssertionFailedError("Method " + enclosingClass.getSimpleName() + ".of does not parameterize the second type parameter of Function"
                + " (" + functionR.getTypeName() + ")");
        }

        Assertions.assertEquals(Sequence.class, pFunctionT.getRawType(),
            "Method " + enclosingClass.getSimpleName() + ".of should parameterize the first type parameter of Function with Sequence");
        Assertions.assertEquals(Sequence.class, pFunctionR.getRawType(),
            "Method " + enclosingClass.getSimpleName() + ".of should parameterize the second type parameter of Function with Sequence");

        final TypeVariable<?> genericT = genericTExtractor.apply(ofMethod);
        final TypeVariable<?> genericR = genericRExtractor.apply(ofMethod);
        Assertions.assertEquals(genericT, pFunctionT.getActualTypeArguments()[0],
            "Method " + enclosingClass.getSimpleName() + ".of should parameterize the first type parameter of"
                + " Function with Sequence<" + genericT.getTypeName() + ">");
        Assertions.assertEquals(genericR, pFunctionR.getActualTypeArguments()[0],
            "Method " + enclosingClass.getSimpleName() + ".of should parameterize the second type parameter of"
                + " Function with Sequence<" + genericR.getTypeName() + ">");

        return ofMethod;
    }
}
