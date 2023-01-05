package h09.operator;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.function.BinaryOperator;

public final class GenericBinaryOperatorExtensions {

    private GenericBinaryOperatorExtensions() {
    }

    static void testApplySignature(final Class<?> binaryOperatorClass) {
        final Method apply = Assertions.assertDoesNotThrow(() ->
                binaryOperatorClass.getDeclaredMethod("apply", Object.class, Object.class),
            binaryOperatorClass.getName() + " should have a method with signatur T apply(T, T)"
        );
        Assertions.assertTrue(Modifier.isPublic(apply.getModifiers()),
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should be public");
        Assertions.assertEquals(binaryOperatorClass.getTypeParameters()[0], apply.getGenericReturnType(),
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should return T");
        final TypeVariable<? extends Class<?>> T = binaryOperatorClass.getTypeParameters()[0];
        Assertions.assertEquals(T, apply.getGenericReturnType(),
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should return T");
        Assertions.assertEquals(T, apply.getGenericParameterTypes()[0],
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should have a first parameter of type T");
        Assertions.assertEquals(T, apply.getGenericParameterTypes()[1],
            "Method " + binaryOperatorClass.getName() + ".apply(T, T) should have a second parameter of type T");
    }

    static <T> void testGenericSuperInterface(final Class<T> binaryOperatorClass, final TypeVariable<Class<T>> genericT) {
        final Type[] ifaces = binaryOperatorClass.getGenericInterfaces();
        Assertions.assertEquals(1, ifaces.length,
            binaryOperatorClass.getSimpleName() + " should implement BinaryOperator<" + genericT.getName() + ">");
        if (ifaces[0] instanceof final ParameterizedType type) {
            Assertions.assertEquals(BinaryOperator.class, type.getRawType(),
                binaryOperatorClass.getSimpleName() + " should implement BinaryOperator<" + genericT.getName() + ">");
            Assertions.assertEquals(1, type.getActualTypeArguments().length,
                binaryOperatorClass.getSimpleName() + " should implement BinaryOperator<" + genericT.getName() + ">");
            Assertions.assertEquals(genericT, type.getActualTypeArguments()[0],
                binaryOperatorClass.getSimpleName() + " should implement BinaryOperator<" + genericT.getName() + ">");
        } else {
            Assertions.fail(binaryOperatorClass.getSimpleName() + " does not parameterize BinaryOperator<" + genericT.getName() + ">");
        }
    }
}
