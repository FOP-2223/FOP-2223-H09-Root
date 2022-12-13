package h09.sequence;

import h09.basic.BasicBinaryOperations;

import java.util.Iterator;

public class SummingSequenceCollector<T> implements SequenceCollector<T, T> {

    private final T identity;
    private final BasicBinaryOperations<T, T> operations;

    public SummingSequenceCollector(T identity, BasicBinaryOperations<T, T> operations) {
        this.identity = identity;
        this.operations = operations;
    }

    @Override
    public T collect(Sequence<T> sequence) {
        T result = identity;
        final Iterator<T> iterator = sequence.iterator();
        while (iterator.hasNext()) {
            result = operations.add(result, iterator.next());
        }
        return result;
    }
}
