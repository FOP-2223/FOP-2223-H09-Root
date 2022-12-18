package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Function;

public class TransformingSequence<T, R> implements Sequence<R> {

    private final Sequence<T> sequence;
    private final Function<? super T, ? extends R> function;

    public static <T, R> Function<Sequence<T>, Sequence<R>> of(Function<? super T, ? extends R> function) {
        return sequence -> new TransformingSequence<>(sequence, function);
    }

    public TransformingSequence(Sequence<T> sequence, Function<? super T, ? extends R> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            private final Iterator<T> iterator = sequence.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return function.apply(iterator.next());
            }
        };
    }
}
