package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class FilteringSequence<T> implements Sequence<T> {

    private final Sequence<T> sequence;
    private final Predicate<? super T> predicate;

    public static <T> Function<Sequence<T>, Sequence<T>> of(Predicate<? super T> predicate) {
        return sequence -> new FilteringSequence<>(sequence, predicate);
    }

    public FilteringSequence(Sequence<T> sequence, Predicate<? super T> predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteringSequenceIterator<>(sequence.iterator(), predicate);
    }
}
