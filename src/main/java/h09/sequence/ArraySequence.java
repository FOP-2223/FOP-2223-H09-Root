package h09.sequence;

import java.util.Iterator;

public class ArraySequence<T> implements Sequence<T> {
    private final T[] values;

    public ArraySequence(T[] values) {
        this.values = values;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySequenceIterator<>(values);
    }
}
