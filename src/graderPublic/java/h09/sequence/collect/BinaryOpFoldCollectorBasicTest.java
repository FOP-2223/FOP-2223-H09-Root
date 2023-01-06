package h09.sequence.collect;

import h09.SignatureTestExtensions;
import h09.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.lang.reflect.TypeVariable;
import java.util.stream.Stream;

@TestForSubmission
public final class BinaryOpFoldCollectorBasicTest {

    @Test
    @SuppressWarnings("rawtypes")
    void testSignature() {
        final TypeVariable<Class<BinaryOpFoldCollector>>[] typeParameters = BinaryOpFoldCollector.class.getTypeParameters();
        Assertions.assertArrayEquals(new String[]{"T"}, Stream.of(typeParameters).map(TypeVariable::getName).toArray(String[]::new),
            "BinaryOpFoldCollector should have a type parameter T");
        final TypeVariable<Class<BinaryOpFoldCollector>> genericT = typeParameters[0];
        Assertions.assertArrayEquals(new Class<?>[]{Object.class}, genericT.getBounds(),
            "BinaryOpFoldCollector's generic type T should not have additional bounds");
        SignatureTestExtensions.testGenericSuperInterface(BinaryOpFoldCollector.class, SequenceCollector.class, genericT, genericT);
    }

    @Test
    void testCollectBasic() {
        final int product = Sequence.of(1, 2, 3, 4, 5).collect(new BinaryOpFoldCollector<>(1, (a, b) -> a * b));
        Assertions.assertEquals(120, product);
    }
}
