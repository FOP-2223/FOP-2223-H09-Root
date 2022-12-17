package h09.sequence.operation;

import h09.sequence.Sequence;

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
        return new OnEachSequenceIterator<>(sequence.iterator(), action);
    }
}
