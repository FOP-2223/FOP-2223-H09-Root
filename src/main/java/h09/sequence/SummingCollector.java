package h09.sequence;

import h09.basic.BasicBinaryOperations;

import java.util.Iterator;

public class SummingCollector<T> implements SequenceCollector<T, T> {

    private final T initial;
    private final BasicBinaryOperations<T, T> operations;

    public SummingCollector(T initial, BasicBinaryOperations<T, T> operations) {
        this.initial = initial;
        this.operations = operations;
    }

    @Override
    public T collect(Sequence<? extends T> sequence) {
        T result = initial;
        final Iterator<? extends T> iterator = sequence.iterator();
        while (iterator.hasNext()) {
            result = operations.add(result, iterator.next());
        }
        return result;
    }
}
