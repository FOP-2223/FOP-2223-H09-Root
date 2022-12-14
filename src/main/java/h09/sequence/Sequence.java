package h09.sequence;

import h09.basic.BasicFactory;

import java.util.Iterator;
import java.util.function.Function;

/**
 * A sequence of discrete values of {@link T}
 *
 * <p>
 * If this sequence is not a {@link Range}, it must, by definition, contain a cycle.
 * </p>
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

    static <T> Sequence<T> of(BasicFactory<T> factory) {
        // TODO: H3.4 - remove if implemented
        throw new RuntimeException("Not implemented yet");
        // Uncomment the following line when implemented:
//        return new BasicFactorySequence<>(factory);
    }

    static <T> Sequence/*TODO: 3.2*/ concat(Sequence/*TODO: 3.2*/ left, Sequence/*TODO: 3.2*/ right) {
        // TODO: H3.2 - remove if implemented
        throw new RuntimeException("Not implemented yet");
    }
}
