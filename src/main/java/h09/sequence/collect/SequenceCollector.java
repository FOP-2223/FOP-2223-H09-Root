package h09.sequence.collect;

import h09.sequence.Sequence;

@FunctionalInterface
public interface SequenceCollector<T, R> {
    R collect(Sequence<? extends T> sequence);
}
