package h09.sequence.operation;

import java.util.Iterator;

class LimitSequenceIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final int limit;
    private int count = 0;

    public LimitSequenceIterator(final Iterator<T> iterator, final int limit) {
        this.iterator = iterator;
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return count < limit && iterator.hasNext();
    }

    @Override
    public T next() {
        count++;
        return iterator.next();
    }
}
