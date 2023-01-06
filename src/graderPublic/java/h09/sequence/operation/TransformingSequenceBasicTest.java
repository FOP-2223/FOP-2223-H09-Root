package h09.sequence.operation;

import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.TypeVariable;
import java.util.Iterator;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class TransformingSequenceBasicTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<TransformingSequence>>[] typeParameters = TransformingSequence.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T", "R"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "TransformingSequence should have two type parameters T and R");
        final TypeVariable<Class<TransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<TransformingSequence>> genericR = typeParameters[1];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "TransformingSequence's generic type T should not have additional bounds");
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericR.getBounds(),
            "TransformingSequence's generic type R should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(TransformingSequence.class, Sequence.class, genericR);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testIteratorBasic() {
        final Sequence sequence = new TransformingSequence(Sequence.of(1, 2, 3, 4, 5), x -> (Integer) x * 10);
        final Iterator it = sequence.iterator();
        for (int i = 0; i < 5; i++) {
            Assertions.assertTrue(it.hasNext(), "TransformingSequence should have 5 elements");
            Assertions.assertEquals((i + 1) * 10, it.next(), "TransformingSequence should have elements 10, 20, 30, 40, 50");
        }
    }
}
