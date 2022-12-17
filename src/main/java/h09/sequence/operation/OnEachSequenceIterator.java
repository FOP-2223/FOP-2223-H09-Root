package h09.sequence.operation;

import java.util.Iterator;
import java.util.function.Consumer;

public class OnEachSequenceIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Consumer<? super T> action;

    public OnEachSequenceIterator(final Iterator<T> iterator, final Consumer<? super T> action) {
        this.iterator = iterator;
        this.action = action;
    }

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
