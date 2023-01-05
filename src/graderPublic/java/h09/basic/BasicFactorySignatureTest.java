package h09.basic;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public final class BasicFactorySignatureTest {

    private BasicFactorySignatureTest() {
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
    static <T> void testSignature(final Class<? extends BasicFactory> factoryClass, final Class<T> type) {
        Assertions.assertFalse(factoryClass.isInterface(),
            factoryClass + " should not be an interface");
        Assertions.assertArrayEquals(EXPECTED_TYPE_PARAMETERS, factoryClass.getTypeParameters(),
            factoryClass + " should not have type parameters");
        final String typeName = type.getSimpleName();
        final Type[] interfaces = factoryClass.getGenericInterfaces();
        Assertions.assertEquals(1, interfaces.length,
            "Expected only one interface (BasicFactory<" + typeName + ">) to be implemented, but found "
                + Arrays.toString(interfaces));
        if (interfaces[0] instanceof ParameterizedType parameterizedType) {
            Assertions.assertEquals(BasicFactory.class, parameterizedType.getRawType(),
                "Expected BasicFactory<" + typeName + "> to be implemented, but found " + parameterizedType);
            Assertions.assertEquals(1, parameterizedType.getActualTypeArguments().length,
                "Expected only one type argument (" + typeName + ") to be implemented, but found "
                    + Arrays.toString(parameterizedType.getActualTypeArguments()));
            Assertions.assertEquals(type, parameterizedType.getActualTypeArguments()[0],
                "Expected BasicFactory<" + typeName + "> to be implemented, but found " + parameterizedType);
        } else {
            Assertions.fail("Expected " + factoryClass + " to parameterize BasicFactory with " + typeName);
        }
    }
}
