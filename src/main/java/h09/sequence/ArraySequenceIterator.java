package h09.sequence;

import java.util.Iterator;

class ArraySequenceIterator<T> implements Iterator<T> {
    private final T[] values;
    private int index = 0;

    public ArraySequenceIterator(T[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public T next() {
        return values[index++];
    }
}
