package h09;

import h09.sequence.FilteringSequence;
import h09.sequence.LimitSequence;
import h09.sequence.OnEachSequence;
import h09.sequence.Sequence;
import h09.sequence.ToListCollector;
import h09.sequence.TransformingSequence;
import org.junit.jupiter.api.Test;

import java.util.List;

class SequenceTest {

    @Test
    void foo() {
        final List<String> result = Sequence.of("test", "foo", "too", "bar", "baz", "abcd", "tree")
            .then(OnEachSequence.apply(s -> System.out.println("on each: " + s)))
            .then(TransformingSequence.apply(String::toUpperCase))
            .then(OnEachSequence.apply(s -> System.out.println("uppercased: " + s)))
            .then(FilteringSequence.apply(s -> s.startsWith("T")))
            .then(OnEachSequence.apply(s -> System.out.println("filtered: " + s)))
            .then(LimitSequence.apply(2))
            .collect(new ToListCollector<>());
        System.out.println("result: " + result);
    }
}
