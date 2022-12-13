package h09.sequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicSequenceTest {

    @Test
    void onEach() {
        final int[] counter = {0};
        Sequence.of(2, 4, 6)
            .then(OnEachSequence.apply(s -> counter[0]++))
            .collect(VoidCollector.get());
        Assertions.assertEquals(3, counter[0]);
    }

    @Test
    void limit() {
        final int[] counter = {0};
        Sequence.of(5, 6, 7, 8, 1, 2)
            .then(LimitSequence.apply(4))
            .then(OnEachSequence.apply(s -> counter[0]++))
            .collect(VoidCollector.get());
        Assertions.assertEquals(4, counter[0]);
    }
}
