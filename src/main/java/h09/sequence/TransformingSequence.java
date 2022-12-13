package h09.sequence;

import java.util.Iterator;
import java.util.function.Function;

public class TransformingSequence<T> implements Sequence<T> {

    private final Sequence<T> sequence;
    private final Function<? super T, ? extends T> function;

    public TransformingSequence(Sequence<T> sequence, Function<? super T, ? extends T> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<T> iterator = sequence.iterator();

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return function.apply(iterator.next());
            }
        };
    }
}
