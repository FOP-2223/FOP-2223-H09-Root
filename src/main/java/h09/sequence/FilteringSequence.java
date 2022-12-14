package h09.sequence;

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
        return new Iterator<>() {
            private final Iterator<T> iterator = sequence.iterator();

            private T next;

            @Override
            public boolean hasNext() {
                while (iterator.hasNext()) {
                    next = iterator.next();
                    if (predicate.test(next)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                return next;
            }
        };
    }
}
