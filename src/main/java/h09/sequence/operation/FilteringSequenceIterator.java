package h09.sequence.operation;

import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Predicate;

class FilteringSequenceIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<? super T> predicate;

    private @Nullable T next;

    public FilteringSequenceIterator(Iterator<T> iterator, Predicate<? super T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
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
        final T result = next;
        next = null;
        return result;
    }
}
