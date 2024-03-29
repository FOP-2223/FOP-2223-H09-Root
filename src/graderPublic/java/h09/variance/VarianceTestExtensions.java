package h09.variance;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class VarianceTestExtensions {

    /**
     * Asserts that the given type has the expected variance.
     */
    public static void assertVariance(final Type baseType, final boolean strict, final VarianceNode... varianceNodes) {
        if (!(baseType instanceof final ParameterizedType baseParameterizedType)) {
            throw new AssertionFailedError("Base type" + baseType.getTypeName() + " is not parameterized");
        }
        Assertions.assertEquals(varianceNodes.length, baseParameterizedType.getActualTypeArguments().length,
            "Incorrect number of variance nodes for base type " + baseType.getTypeName());
        final Type[] actualTypeArguments = baseParameterizedType.getActualTypeArguments();
        for (int i = 0; i < varianceNodes.length; i++) {
            varianceNodes[i].check(actualTypeArguments[i], strict);
        }
    }

    public static void assertStrictVariance(final Type baseType, final VarianceNode... varianceNodes) {
        assertVariance(baseType, true, varianceNodes);
    }
}
