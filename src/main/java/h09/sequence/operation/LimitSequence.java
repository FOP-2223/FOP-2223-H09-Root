package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;

public class LimitSequence<T> implements Sequence<T> {

    private final Sequence<T> sequence;
    private final int limit;

    public static <T> Function<Sequence<T>, Sequence<T>> of(int limit) {
        return sequence -> new LimitSequence<>(sequence, limit);
    }

    public LimitSequence(Sequence<T> sequence, int limit) {
        this.sequence = sequence;
        this.limit = limit;
    }

    @Override
    public Iterator<T> iterator() {
        return new LimitSequenceIterator<>(sequence.iterator(), limit);
    }
}
