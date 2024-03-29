package h09.sequence.collect;

import h09.sequence.Sequence;

/**
 * A collector is a function that collects a sequence of type {@link T} into a result of type {@link R}.
 */
@FunctionalInterface
public interface SequenceCollector<T, R> {

    /**
     * Collects the given sequence of type {@link T} into a result of type {@link R}.
     *
     * @param sequence the sequence to collect
     * @return the result of the collection
     */
    R collect(Sequence<? extends T> sequence);
}
