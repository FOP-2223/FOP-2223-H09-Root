package h09.sequence;

import h09.basic.BasicFactory;
import h09.sequence.collect.SequenceCollector;

import java.util.Iterator;
import java.util.function.Function;

/**
 * A sequence of discrete values of {@link T}.
 */
@FunctionalInterface
public interface Sequence<T> {

    /**
     * Creates an iterator for this sequence.
     */
    Iterator<T> iterator();

    default <R> Sequence<R> then(Function<Sequence<T>, Sequence<R>> sequenceMapper) {
        return sequenceMapper.apply(this);
    }

    default <R> R collect(SequenceCollector<T, R> collector) {
        return collector.collect(this);
    }

    @SafeVarargs
    static <T> Sequence<T> of(T... elements) {
        // TODO: H3.1 - remove if implemented
        throw new RuntimeException("Not implemented yet");
        // Uncomment the following line when implemented:
//        return new ArraySequence<>(elements);
    }

    static <T> Sequence<T> of(Iterable<T> iterable) {
        return iterable::iterator;
    }

    static <T> Sequence<T> of(BasicFactory<T> factory) {
        // TODO: H3.4 - remove if implemented
        throw new RuntimeException("Not implemented yet");
        // Uncomment the following line when implemented:
//        return new BasicFactorySequence<>(factory);
    }
}
