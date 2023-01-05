package h09.operator;

import h09.SignatureTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.BinaryOperator;

@TestForSubmission
public class H2_1_Test {

    private abstract static class AbstractTest {

        private final Class<?> targetClass;

        protected AbstractTest(final Class<?> targetClass) {
            this.targetClass = targetClass;
        }

        @Test
        void testClassSignature() {
            SignatureTestExtensions.testSignature(targetClass, BinaryOperator.class, Double.class);
        }

        @Test
        void testApplySignature() {
            final Method apply = Assertions.assertDoesNotThrow(() ->
                    DoubleMaxOfTwoOperator.class.getDeclaredMethod("apply", Double.class, Double.class),
                "DoubleMaxOfTwoOperator should have a method with signature apply(Double, Double)");
            Assertions.assertTrue(Modifier.isPublic(apply.getModifiers()),
                "Method DoubleMaxOfTwoOperator.apply(Double, Double) should be public");
            Assertions.assertEquals(Double.class, apply.getReturnType(),
                "Method DoubleMaxOfTwoOperator.apply(Double, Double) should return a Double");
        }
    }

    @Nested
    final class ComposedDoubleBinaryOperatorTest extends AbstractTest {
        public ComposedDoubleBinaryOperatorTest() {
            super(ComposedDoubleBinaryOperator.class);
        }

        @Test
        void testFields() {
            final Field[] fields = ComposedDoubleBinaryOperator.class.getDeclaredFields();
            Assertions.assertEquals(3, fields.length, "ComposedDoubleBinaryOperator should have 3 fields");
            for (final Field field : fields) {
                Assertions.assertTrue(Modifier.isFinal(field.getModifiers()),
                    "Field ComposedDoubleBinaryOperator." + field.getName() + " should be final");
                Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()),
                    "Field ComposedDoubleBinaryOperator." + field.getName() + " should be private");
                Assertions.assertEquals(BinaryOperator.class, field.getType(),
                    "Field ComposedDoubleBinaryOperator." + field.getName() + " should be of type BinaryOperator<Double>");
                if (field.getGenericType() instanceof ParameterizedType type) {
                    Assertions.assertEquals(Double.class, type.getActualTypeArguments()[0],
                        "Field ComposedDoubleBinaryOperator." + field.getName() + " should be of type BinaryOperator<Double>");
                } else {
                    Assertions.fail("Field ComposedDoubleBinaryOperator." + field.getName() + " is not a parameterized type");
                }
            }
        }

        @Test
        void testConstructor() {
            final Constructor<ComposedDoubleBinaryOperator> constructor = Assertions.assertDoesNotThrow(() ->
                    ComposedDoubleBinaryOperator.class.getDeclaredConstructor(
                        BinaryOperator.class, BinaryOperator.class, BinaryOperator.class),
                "ComposedDoubleBinaryOperator should have a constructor with signature"
                    + "ComposedDoubleBinaryOperator(BinaryOperator<Double>, BinaryOperator<Double>, BinaryOperator<Double>)"
            );
            Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
                "Constructor ComposedDoubleBinaryOperator(BinaryOperator<Double>, BinaryOperator<Double>, BinaryOperator<Double>) should be public");
            final Type[] genericParams = constructor.getGenericParameterTypes();
            for (int i = 0; i < 3; i++) {
                if (genericParams[i] instanceof ParameterizedType type) {
                    Assertions.assertEquals(Double.class, type.getActualTypeArguments()[0],
                        "Parameter " + (i + 1) + " of ComposedDoubleBinaryOperator should be of type BinaryOperator<Double>");
                } else {
                    Assertions.fail("Parameter " + (i + 1) + " of ComposedDoubleBinaryOperator is not a parameterized type");
                }
            }
        }

        @Test
        void testApply() {
            final BinaryOperator<Double> op1 = (a, b) -> a + b;
            final BinaryOperator<Double> op2 = (a, b) -> a * b;
            final BinaryOperator<Double> op3 = (a, b) -> a - b;
            final ComposedDoubleBinaryOperator operator =
                Assertions.assertDoesNotThrow(() -> new ComposedDoubleBinaryOperator(op1, op2, op3),
                    "Failed to invoke constructor with signature"
                        + "ComposedDoubleBinaryOperator(BinaryOperator<Double>, BinaryOperator<Double>, BinaryOperator<Double>)"
                );
            Assertions.assertEquals(0.5, operator.apply(1.5, 2.0),
                "ComposedDoubleBinaryOperator.apply(1.5, 2.0) should return 0.5");
            Assertions.assertEquals(1.0, operator.apply(1.0, 2.0),
                "ComposedDoubleBinaryOperator.apply(1.0, 2.0) should return 1.0");
            Assertions.assertEquals(-16.5, operator.apply(-4.0, -2.5),
                "ComposedDoubleBinaryOperator.apply(-4.0, -2.5) should return -16.5");
        }
    }

    @Nested
    final class DoubleMaxOfTwoOperatorTest extends AbstractTest {
        public DoubleMaxOfTwoOperatorTest() {
            super(DoubleMaxOfTwoOperator.class);
        }

        @Test
        void testApply() {
            final DoubleMaxOfTwoOperator operator = new DoubleMaxOfTwoOperator();
            Assertions.assertEquals(2.0, operator.apply(1.5, 2.0),
                "DoubleMaxOfTwoOperator.apply(1.5, 2.0) should return 2.0");
            Assertions.assertEquals(2.0, operator.apply(1.0, 2.0),
                "DoubleMaxOfTwoOperator.apply(1.0, 2.0) should return 2.0");
            Assertions.assertEquals(-2.5, operator.apply(-4.0, -2.5),
                "DoubleMaxOfTwoOperator.apply(-4.0, -2.5) should return -2.5");
        }
    }

    @Nested
    final class DoubleSumWithCoefficientsOperatorTest extends AbstractTest {
        public DoubleSumWithCoefficientsOperatorTest() {
            super(DoubleSumWithCoefficientsOperator.class);
        }

        @Test
        void testFields() {
            final Field[] fields = DoubleSumWithCoefficientsOperator.class.getDeclaredFields();
            Assertions.assertEquals(2, fields.length, "DoubleSumWithCoefficientsOperator should have 2 fields");
            for (final Field field : fields) {
                Assertions.assertTrue(Modifier.isFinal(field.getModifiers()),
                    "Field DoubleSumWithCoefficientsOperator." + field.getName() + " should be final");
                Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()),
                    "Field DoubleSumWithCoefficientsOperator." + field.getName() + " should be private");
                Assertions.assertEquals(Double.class, field.getType(),
                    "Field DoubleSumWithCoefficientsOperator." + field.getName() + " should be of type Double");
            }
        }

        @Test
        void testConstructor() {
            final Constructor<DoubleSumWithCoefficientsOperator> constructor = Assertions.assertDoesNotThrow(() ->
                    DoubleSumWithCoefficientsOperator.class.getDeclaredConstructor(Double.class, Double.class),
                "DoubleSumWithCoefficientsOperator should have a constructor with signature"
                    + "DoubleSumWithCoefficientsOperator(Double, Double)");
            Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
                "Constructor DoubleSumWithCoefficientsOperator(Double, Double) should be public");
        }

        @Test
        void testApply() {
            final DoubleSumWithCoefficientsOperator operator =
                Assertions.assertDoesNotThrow(() -> new DoubleSumWithCoefficientsOperator(3.0, -2.5),
                    "Failed to invoke constructor with signature"
                        + "DoubleSumWithCoefficientsOperator(Double, Double)");
            Assertions.assertEquals(-0.5, operator.apply(1.5, 2.0),
                "DoubleSumWithCoefficientsOperator(3.0, -2.5).apply(1.5, 2.0) should return -0.5");
            Assertions.assertEquals(7.25, operator.apply(0.75, -2.0),
                "DoubleSumWithCoefficientsOperator(3.0, -2.5).apply(0.75, -2.0) should return 7.25");
            Assertions.assertEquals(-5.75, operator.apply(-4.0, -2.5),
                "DoubleSumWithCoefficientsOperator(3.0, -2.5).apply(-4.0, -2.5) should return -5.75");
        }
    }
}
