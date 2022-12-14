package h09.sequence;

import java.util.Iterator;
import java.util.function.Function;

public class FlatteningTransformingSequence<T, S extends Sequence<T>, R> implements Sequence<R> {

    private final Sequence<S> sequence;
    private final Function<? super T, ? extends R> function;

    public static <T, S extends Sequence<T>, R> Function<Sequence<S>, Sequence<R>> of(Function<? super T, ? extends R> function) {
        return sequence -> new FlatteningTransformingSequence<>(sequence, function);
    }

    public FlatteningTransformingSequence(Sequence<S> sequence, Function<? super T, ? extends R> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            private final Iterator<S> iterator = sequence.iterator();
            private Iterator<T> currentIterator = null;

            @Override
            public boolean hasNext() {
                if (currentIterator == null) {
                    if (iterator.hasNext()) {
                        currentIterator = iterator.next().iterator();
                    } else {
                        return false;
                    }
                }
                if (currentIterator.hasNext()) {
                    return true;
                } else {
                    currentIterator = null;
                    return hasNext();
                }
            }

            @Override
            public R next() {
                return function.apply(currentIterator.next());
            }
        };
    }
}
