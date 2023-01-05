package h09.operator;

import h09.basic.BasicBinaryOperations;
import h09.basic.StringBasicBinaryOperations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public class SumWithCoefficientsOperatorTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<SumWithCoefficientsOperator>>[] typeParameters = SumWithCoefficientsOperator.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"X", "Y"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "SumWithCoefficientsOperator should have exactly two type parameters X and Y");
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericX = typeParameters[0];
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericY = typeParameters[1];
        Assertions.assertEquals(1, genericX.getBounds().length,
            "SumWithCoefficientsOperator should not have additional bounds on type parameter X");
        Assertions.assertEquals(1, genericY.getBounds().length,
            "SumWithCoefficientsOperator should not have additional bounds on type parameter Y");
        Assertions.assertEquals(Object.class, genericX.getBounds()[0],
            "ComposedBinaryOperator should have a type parameter X with bound Object");
        Assertions.assertEquals(Object.class, genericY.getBounds()[0],
            "ComposedBinaryOperator should have a type parameter Y with bound Object");
        GenericBinaryOperatorExtensions.testGenericSuperInterface(SumWithCoefficientsOperator.class, genericX);
    }

    @Test
    void testFields() {
        final TypeVariable<Class<SumWithCoefficientsOperator>>[] typeParameters = SumWithCoefficientsOperator.class.getTypeParameters();
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericX = typeParameters[0];
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericY = typeParameters[1];
        final Field[] fields = SumWithCoefficientsOperator.class.getDeclaredFields();
        Assertions.assertEquals(2, Stream.of(fields).filter(field -> field.getGenericType().equals(genericY)).count(),
            "SumWithCoefficientsOperator should have exactly two fields of generic type Y");
        final Field opField = Stream.of(fields).filter(field -> field.getType().equals(BasicBinaryOperations.class)).findAny()
            .orElseThrow(() -> new AssertionFailedError("SumWithCoefficientsOperator should have a field who's raw type is BasicBinaryOperations"));
        if (opField.getGenericType() instanceof ParameterizedType type) {
            final Type[] actualTypeArguments = type.getActualTypeArguments();
            Assertions.assertArrayEquals(new Type[]{genericX, genericY}, actualTypeArguments,
                "SumWithCoefficientsOperator should have a field who's raw type is BasicBinaryOperations<X, Y>");
        } else {
            Assertions.fail("Field SumWithCoefficientsOperator." + opField.getName() + " should be of a parameterized type");
        }
    }

    @Test
    void testConstructor() {
        final Constructor<SumWithCoefficientsOperator> constructor = Assertions.assertDoesNotThrow(() ->
                SumWithCoefficientsOperator.class.getDeclaredConstructor(
                    BasicBinaryOperations.class, Object.class, Object.class),
            "SumWithCoefficientsOperator should have a constructor with signature"
                + "SumWithCoefficientsOperator(BinaryOperator<X, Y>, Y, Y)"
        );
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "Constructor SumWithCoefficientsOperator(BinaryOperator<X, Y>, Y, Y) should be public");
        final Type[] genericParams = constructor.getGenericParameterTypes();
        final TypeVariable<Class<SumWithCoefficientsOperator>>[] typeParameters = SumWithCoefficientsOperator.class.getTypeParameters();
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericX = typeParameters[0];
        final TypeVariable<Class<SumWithCoefficientsOperator>> genericY = typeParameters[1];
        if (genericParams[0] instanceof final ParameterizedType type) {
            Assertions.assertArrayEquals(new Type[]{genericX, genericY}, type.getActualTypeArguments(),
                "Constructor SumWithCoefficientsOperator(BinaryOperator<X, Y>, Y, Y) should have a first parameter of type BinaryOperator<X, Y>");
        } else {
            Assertions.fail("The first parameter of constructor SumWithCoefficientsOperator(BinaryOperator<X, Y>, Y, Y) should be parameterized");
        }
        Assertions.assertEquals(genericY, genericParams[1],
            "Constructor SumWithCoefficientsOperator(BinaryOperator<X, Y>, Y, Y) should have a second parameter of type Y");
        Assertions.assertEquals(genericY, genericParams[2],
            "Constructor SumWithCoefficientsOperator(BinaryOperator<X, Y>, Y, Y) should have a third parameter of type Y");
    }

    @Test
    void testApplySignature() {
        GenericBinaryOperatorExtensions.testApplySignature(SumWithCoefficientsOperator.class);
    }

    @Test
    void testApply() {
        final StringBasicBinaryOperations op = new StringBasicBinaryOperations();
        final SumWithCoefficientsOperator<String, Integer> sum = new SumWithCoefficientsOperator<>(op, 3, 2);
        Assertions.assertEquals("abababcdcd", sum.apply("ab", "cd"),
            "new SumWithCoefficientsOperator(new StringBasicBinaryOperations(), 3, 2).apply(\"ab\", \"cd\") should return \"abababcdcd\"");
        Assertions.assertEquals("aaacdaacdaa", sum.apply("a", "cdaa"),
            "new SumWithCoefficientsOperator(new StringBasicBinaryOperations(), 3, 2).apply(\"a\", \"cdaa\") should return \"aaacdaacdaa\"");
    }
}
