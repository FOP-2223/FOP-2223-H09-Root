package h09.operator;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class ComposedBinaryOperatorTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<ComposedBinaryOperator>>[] typeParameters = ComposedBinaryOperator.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "ComposedBinaryOperator should have a single type parameter T");
        final TypeVariable<Class<ComposedBinaryOperator>> typeParameter = typeParameters[0];
        Assertions.assertEquals(1, typeParameter.getBounds().length, "ComposedBinaryOperator should not have additional bounds on type parameter T");
        Assertions.assertEquals(Object.class, typeParameter.getBounds()[0], "ComposedBinaryOperator should have a type parameter T with bound Object");
        GenericBinaryOperatorExtensions.testGenericSuperInterface(ComposedBinaryOperator.class, typeParameter);
    }

    @Test
    void testFields() {
        final TypeVariable<Class<ComposedBinaryOperator>> T = ComposedBinaryOperator.class.getTypeParameters()[0];
        final Field[] fields = ComposedBinaryOperator.class.getDeclaredFields();
        Assertions.assertEquals(3, fields.length, "ComposedBinaryOperator should have three fields");
        for (final Field field : fields) {
            Assertions.assertTrue(Modifier.isFinal(field.getModifiers()),
                "ComposedBinaryOperator." + field.getName() + " should be final");
            Assertions.assertTrue(Modifier.isPrivate(field.getModifiers()),
                "ComposedBinaryOperator." + field.getName() + " should be private");
            Assertions.assertEquals(BinaryOperator.class, field.getType(),
                "ComposedBinaryOperator." + field.getName() + " should be of type BinaryOperator<T>");
            if (field.getGenericType() instanceof final ParameterizedType type) {
                Assertions.assertEquals(T, type.getActualTypeArguments()[0],
                    "Field ComposedBinaryOperator." + field.getName() + " should be of type BinaryOperator<T>");
            } else {
                Assertions.fail("Field ComposedBinaryOperator." + field.getName() + " is not a parameterized type");
            }
        }
    }

    @Test
    void testConstructor() {
        final Constructor<ComposedBinaryOperator> constructor = Assertions.assertDoesNotThrow(() ->
                ComposedBinaryOperator.class.getDeclaredConstructor(
                    BinaryOperator.class, BinaryOperator.class, BinaryOperator.class),
            "ComposedBinaryOperator should have a constructor with signature"
                + "ComposedBinaryOperator(BinaryOperator<T>, BinaryOperator<T>, BinaryOperator<T>)"
        );
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "Constructor ComposedBinaryOperator(BinaryOperator<T>, BinaryOperator<T>, BinaryOperator<T>) should be public");
        final Type[] genericParams = constructor.getGenericParameterTypes();
        final TypeVariable<Class<ComposedBinaryOperator>> T = ComposedBinaryOperator.class.getTypeParameters()[0];
        for (int i = 0; i < 3; i++) {
            if (genericParams[i] instanceof final ParameterizedType type) {
                Assertions.assertEquals(T, type.getActualTypeArguments()[0],
                    "Parameter " + i + " of ComposedDoubleBinaryOperator should be of type BinaryOperator<T>");
            } else {
                Assertions.fail("Parameter " + i + " of ComposedDoubleBinaryOperator is not a parameterized type");
            }
        }
    }

    @Test
    void testApplySignature() {
        GenericBinaryOperatorExtensions.testApplySignature(ComposedBinaryOperator.class);
    }

    @Test
    void testApply() {
        final BinaryOperator<String> concat = (a, b) -> a + b;
        final BinaryOperator<String> intersect = (a, b) -> {
            final Set<Character> foo = Sets.intersection(
                a.chars().mapToObj(c -> (char) c).collect(HashSet::new, HashSet::add, HashSet::addAll),
                b.chars().mapToObj(c -> (char) c).collect(HashSet::new, HashSet::add, HashSet::addAll)
            );
            return foo.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        };
        final ComposedBinaryOperator<String> operator =
            Assertions.assertDoesNotThrow(() -> new ComposedBinaryOperator<>(concat, intersect, concat),
                "ComposedBinaryOperator should have a constructor with signature"
                    + "ComposedBinaryOperator(BinaryOperator<T>, BinaryOperator<T>, BinaryOperator<T>)"
            );
        Assertions.assertEquals("abbcb", operator.apply("ab", "bc"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"ab\", \"bc\") should return \"abbcb\"");
        Assertions.assertEquals("abcd", operator.apply("ab", "cd"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"ab\", \"cd\") should return \"abcd\"");
        Assertions.assertEquals("ababab", operator.apply("ab", "ab"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"ab\", \"ab\") should return \"ababab\"");
        Assertions.assertEquals("helloworldlo", operator.apply("hello", "world"),
            "ComposedBinaryOperator(concat, intersect, concat).apply(\"hello\", \"world\") should return \"helloworldlo\"");
    }
}
