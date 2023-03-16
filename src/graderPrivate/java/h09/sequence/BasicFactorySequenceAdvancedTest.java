package h09.sequence;

import com.google.common.collect.Iterables;
import h09.FieldExtensions;
import h09.InvokeAssertions;
import h09.basic.BasicFactory;
import h09.variance.Variance;
import h09.variance.VarianceNode;
import h09.variance.VarianceTestExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.Iterator;

@TestForSubmission
@SuppressWarnings("rawtypes")
public class BasicFactorySequenceAdvancedTest {

    @Test
    void testFields() {
        final TypeVariable<Class<BasicFactorySequence>> genericT = BasicFactorySequence.class.getTypeParameters()[0];
        final Field[] fields = BasicFactorySequence.class.getDeclaredFields();
        Assertions.assertEquals(1, fields.length, "BasicFactorySequence should have exactly 1 field");
        FieldExtensions.assertPrivateFinal("BasicFactorySequence", fields);

        final Field factoryField = fields[0];
        Assertions.assertEquals(BasicFactory.class, factoryField.getType(),
            "BasicFactorySequence should have a field who's raw type is BasicFactory");

        VarianceTestExtensions.assertStrictVariance(factoryField.getGenericType(),
            new VarianceNode(genericT, Variance.INVARIANT));
    }

    @Test
    void testConstructor() {
        final TypeVariable<Class<BasicFactorySequence>> genericT = BasicFactorySequence.class.getTypeParameters()[0];
        final Constructor<BasicFactorySequence> constructor = Assertions.assertDoesNotThrow(() ->
                BasicFactorySequence.class.getDeclaredConstructor(BasicFactory.class),
            "BasicFactorySequence should have a constructor with a parameter who's raw type is BasicFactory");
        Assertions.assertTrue(Modifier.isPublic(constructor.getModifiers()),
            "BasicFactorySequence's constructor should be public");
        final Parameter parameter = constructor.getParameters()[0];
        VarianceTestExtensions.assertStrictVariance(parameter.getParameterizedType(),
            new VarianceNode(genericT, Variance.INVARIANT));
    }

    @Test
    void testIterator() {
        final Constructor<BasicFactorySequence> constructor = Assertions.assertDoesNotThrow(() ->
                BasicFactorySequence.class.getDeclaredConstructor(BasicFactory.class),
            "BasicFactorySequence should have a constructor with a parameter who's raw type is BasicFactory");
        final Iterable<String> backing = Iterables.cycle("abcabc", "defdef", "ghighi", "jkljkl");
        final Iterator<String> backingIterator = backing.iterator();
        final BasicFactory<String> basicFactory = backingIterator::next;
        final BasicFactorySequence sequence = InvokeAssertions.assertDoesNotThrow(() ->
                constructor.newInstance(basicFactory),
            "Failed to invoke BasicFactorySequence constructor"
        );
        final Iterator iterator = sequence.iterator();
        final Iterator<String> expected = backing.iterator();
        for (int i = 0; i < 18; i++) {
            Assertions.assertTrue(iterator.hasNext(),
                "BasicFactorySequence iterator should always have a next element");
            Assertions.assertEquals(expected.next(), iterator.next(),
                "BasicFactorySequence iterator should return the correct element");
        }
    }
}
