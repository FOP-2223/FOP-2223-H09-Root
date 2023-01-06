package h09.sequence.operation;

import h09.SignatureTestExtensions;
import h09.sequence.PrimitiveSequence;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.TypeVariable;
import java.util.Iterator;
import java.util.stream.Stream;

@TestForSubmission
@SuppressWarnings("rawtypes")
public final class FlatteningTransformingSequenceBasicTest {

    @Test
    void testSignature() {
        final TypeVariable<Class<FlatteningTransformingSequence>>[] typeParameters = FlatteningTransformingSequence.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T", "R"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "FlatteningTransformingSequence should have two type parameters T and R");
        final TypeVariable<Class<FlatteningTransformingSequence>> genericT = typeParameters[0];
        final TypeVariable<Class<FlatteningTransformingSequence>> genericR = typeParameters[1];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "FlatteningTransformingSequence's generic type T should not have additional bounds");
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericR.getBounds(),
            "FlatteningTransformingSequence's generic type R should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(FlatteningTransformingSequence.class, Sequence.class, genericR);
    }

    @Test
    void testIteratorBasic() {
        Sequence<String> ogSeq = Sequence.of("1", "23", "456");
        Sequence<Character> charSeq = new FlatteningTransformingSequence<>(ogSeq, s -> PrimitiveSequence.of(s.toCharArray()));
        Sequence<Integer> seq = new TransformingSequence<>(charSeq, Character::getNumericValue);
        Sequence<Integer> limitedSeq = new LimitSequence<>(seq, 6);
        Iterator<Integer> it = limitedSeq.iterator();
        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(it.hasNext(), "FlatteningTransformingSequence should have 6 elements");
            Assertions.assertEquals(i + 1, it.next(), "FlatteningTransformingSequence should have elements 1, 2, 3, 4, 5, 6");
        }
    }
}
