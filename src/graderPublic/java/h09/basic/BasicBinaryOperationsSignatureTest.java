package h09.basic;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class BasicBinaryOperationsSignatureTest {

    private BasicBinaryOperationsSignatureTest() {
    }

    @SuppressWarnings("unchecked")
    private static final TypeVariable<Class<?>>[] EXPECTED_TYPE_PARAMETERS =
        (TypeVariable<Class<?>>[]) new TypeVariable<?>[0];

    /**
     * Tests whether the given class has the correct signature.
     *
     * <p>
     * The type for {@code factoryClass} should technically be
     * {@code Class<? extends BasicFactory<T>>}, but we must allow for
     * the fact that the student may have used a raw type.
     * </p>
     */
    @SuppressWarnings("rawtypes")
    static <X, Y> void testSignature(final Class<? extends BasicBinaryOperations> opClass,
                                     final Class<X> leftType, final Class<Y> rightType) {
        Assertions.assertFalse(opClass.isInterface(),
            opClass + " should not be an interface");
        Assertions.assertArrayEquals(EXPECTED_TYPE_PARAMETERS, opClass.getTypeParameters(),
            opClass + " should not have type parameters");
        final String leftTypeName = leftType.getSimpleName();
        final String rightTypeName = rightType.getSimpleName();
        final Type[] interfaces = opClass.getGenericInterfaces();
        Assertions.assertEquals(1, interfaces.length,
            "Expected only one interface (BasicBinaryOperations<"
                + leftTypeName + ", " + rightTypeName + ">) to be implemented, but found "
                + Arrays.toString(interfaces));
        if (interfaces[0] instanceof ParameterizedType parameterizedType) {
            Assertions.assertEquals(BasicBinaryOperations.class, parameterizedType.getRawType(),
                "Expected BasicFactory<" + leftTypeName + ", " + rightTypeName
                    + "> to be implemented, but found " + parameterizedType);
            Assertions.assertEquals(2, parameterizedType.getActualTypeArguments().length,
                "Expected exactly two type arguments (" + leftTypeName + ", " + rightTypeName
                    + ") to be implemented, but found "
                    + Arrays.toString(parameterizedType.getActualTypeArguments()));
            Assertions.assertEquals(leftType, parameterizedType.getActualTypeArguments()[0],
                "Expected first type parameter to be " + leftTypeName
                    + " to be implemented, but found " + parameterizedType);
            Assertions.assertEquals(rightType, parameterizedType.getActualTypeArguments()[1],
                "Expected second type parameter to be " + rightTypeName
                    + " to be implemented, but found " + parameterizedType);
        } else {
            Assertions.fail("Expected " + opClass + " to parameterize BasicFactory with " + leftTypeName
                + " and " + rightTypeName);
        }
    }
}
