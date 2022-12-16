package h09.sequence;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class OnEachSequence<T> implements Sequence<T> {

    private final Sequence<T> sequence;
    private final Consumer<? super T> action;

    public static <T> Function<Sequence<T>, Sequence<T>> of(Consumer<? super T> action) {
        return sequence -> new OnEachSequence<>(sequence, action);
    }

    public OnEachSequence(Sequence<T> sequence, Consumer<? super T> action) {
        this.sequence = sequence;
        this.action = action;
    }

    @Override
    public Iterator<T> iterator() {
        return new OnEachSequenceIterator();
    }

    private class OnEachSequenceIterator implements Iterator<T> {
        private final Iterator<T> iterator = sequence.iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            T next = iterator.next();
            action.accept(next);
            return next;
        }
    }
}
