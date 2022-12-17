package h09.sequence;

import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Predicate;

class FilteringSequenceIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<? super T> predicate;

    private @Nullable T next;

    public FilteringSequenceIterator(Predicate<? super T> predicate, Iterator<T> iterator) {
        this.predicate = predicate;
        this.iterator = iterator;
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
