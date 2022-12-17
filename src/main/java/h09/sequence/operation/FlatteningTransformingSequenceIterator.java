package h09.sequence.operation;

import h09.sequence.Sequence;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Function;

public class FlatteningTransformingSequenceIterator<T, R> implements Iterator<R> {
    private final Iterator<T> iterator;
    private final Function<? super T, ? extends Sequence<? extends R>> function;
    private @Nullable Iterator<? extends R> currentIterator = null;

    public FlatteningTransformingSequenceIterator(
        final Iterator<T> iterator,
        final Function<? super T, ? extends Sequence<? extends R>> function
    ) {
        this.iterator = iterator;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        while (currentIterator == null || !currentIterator.hasNext()) {
            if (!iterator.hasNext()) {
                return false;
            }
            currentIterator = function.apply(iterator.next()).iterator();
        }
        return true;
    }

    @Override
    public R next() {
        if (currentIterator == null) {
            throw new IllegalStateException("next() called before hasNext()");
        }
        return currentIterator.next();
    }
}
