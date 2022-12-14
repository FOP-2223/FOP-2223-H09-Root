package h09.sequence;

import java.util.Iterator;
import java.util.function.Function;

public class FlatteningTransformingSequence<T, S extends Sequence<R>, R> implements Sequence<R> {

    private final Sequence<T> sequence;
    private final Function<? super T, ? extends S> function;

    public static <T, S extends Sequence<R>, R> Function<Sequence<T>, Sequence<R>> of(Function<? super T, ? extends S> function) {
        return sequence -> new FlatteningTransformingSequence<>(sequence, function);
    }

    public FlatteningTransformingSequence(Sequence<T> sequence, Function<? super T, ? extends S> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            private final Iterator<T> iterator = sequence.iterator();
            private Iterator<R> currentIterator = null;

            @Override
            public boolean hasNext() {
                if (currentIterator != null && currentIterator.hasNext()) {
                    return true;
                }
                while (iterator.hasNext()) {
                    currentIterator = function.apply(iterator.next()).iterator();
                    if (currentIterator.hasNext()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public R next() {
                return currentIterator.next();
            }
        };
    }
}
