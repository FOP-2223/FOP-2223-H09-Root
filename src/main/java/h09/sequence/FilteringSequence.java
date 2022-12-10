package h09.sequence;

import h09.Sequence;

import java.util.Iterator;
import java.util.function.Predicate;

public class FilteringSequence<T> implements Sequence<T> {

    private final Sequence<T> sequence;
    private final Predicate<? super T> predicate;

    public FilteringSequence(Sequence<T> sequence, Predicate<? super T> predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
