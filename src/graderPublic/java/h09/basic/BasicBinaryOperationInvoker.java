package h09.basic;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;

@SuppressWarnings({"rawtypes"})
public final class BasicBinaryOperationInvoker {

    private BasicBinaryOperationInvoker() {
    }

    private static Object invokeMethod(final BasicBinaryOperations operations,
                                       final Object left, final Object right,
                                       final String methodName) {
        final Method method = Assertions.assertDoesNotThrow(() ->
                operations.getClass().getDeclaredMethod(methodName, Object.class, Object.class),
            "Could not find " + methodName + " method in " + operations.getClass().getSimpleName());
        return Assertions.assertDoesNotThrow(() -> method.invoke(operations, left, right),
            "Could not invoke " + methodName + " method in " + operations.getClass().getSimpleName());
    }

    static Object invokeAdd(final BasicBinaryOperations operations, final Object left, final Object right) {
        return invokeMethod(operations, left, right, "add");
    }

    static Object invokeMul(final BasicBinaryOperations operations, final Object left, final Object right) {
        return invokeMethod(operations, left, right, "mul");
    }
}
