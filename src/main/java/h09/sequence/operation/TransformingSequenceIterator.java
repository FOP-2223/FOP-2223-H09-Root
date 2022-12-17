package h09.sequence.operation;

import java.util.Iterator;
import java.util.function.Function;

public class TransformingSequenceIterator<T, R> implements Iterator<R> {
    private final Iterator<T> iterator;
    private final Function<? super T, ? extends R> function;

    public TransformingSequenceIterator(Iterator<T> iterator, Function<? super T, ? extends R> function) {
        this.iterator = iterator;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R next() {
        return function.apply(iterator.next());
    }
}
