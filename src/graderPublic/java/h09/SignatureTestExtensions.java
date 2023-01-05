package h09;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class SignatureTestExtensions {

    @SuppressWarnings("unchecked")
    private static final TypeVariable<Class<?>>[] EXPECTED_TYPE_PARAMETERS =
        (TypeVariable<Class<?>>[]) new TypeVariable<?>[0];

    /**
     * Tests whether the given class has the correct signature.
     */
    public static void testSignature(final Class<?> targetClass,
                                     final Class<?> targetSuperInterface,
                                     final Class<?>... paramTypes) {
        Assertions.assertFalse(targetClass.isInterface(),
            targetClass + " should not be an interface");
        Assertions.assertArrayEquals(EXPECTED_TYPE_PARAMETERS, targetClass.getTypeParameters(),
            targetClass + " should not have type parameters");
        final String className = targetClass.getSimpleName();
        final String superInterfaceName = targetSuperInterface.getSimpleName();
        final String[] paramNames = Arrays.stream(paramTypes).map(Class::getSimpleName).toArray(String[]::new);
        final String params = String.join(", ", paramNames);
        final String superInterfaceSignature = superInterfaceName + "<" + params + ">";
        final Type[] interfaces = targetClass.getGenericInterfaces();
        Assertions.assertEquals(1, interfaces.length,
            "Expected " + className + " to implement exactly one interface " + superInterfaceSignature
                + ", but found " + Arrays.toString(interfaces));
        if (interfaces[0] instanceof final ParameterizedType parameterizedType) {
            Assertions.assertEquals(targetSuperInterface, parameterizedType.getRawType(),
                "Expected " + className + " to implement exactly one interface " + superInterfaceSignature
                    + ", but found " + parameterizedType);
            Assertions.assertEquals(paramNames.length, parameterizedType.getActualTypeArguments().length,
                "Expected " + className + " to parameterize " + superInterfaceName
                    + " with exactly " + paramNames.length + " type argument(s): (" + params + "), but found "
                    + Arrays.toString(parameterizedType.getActualTypeArguments()));
            for (int i = 0; i < paramTypes.length; i++) {
                final Type actual = parameterizedType.getActualTypeArguments()[i];
                Assertions.assertEquals(paramTypes[i], actual,
                    "Expected type parameter " + paramNames[i] + " to be parameterized at position " + i
                        + " in " + className + ", but found " + actual);
            }
        } else {
            Assertions.fail("Expected " + className + " to parameterize " + targetSuperInterface + " with " + params);
        }
    }
}
