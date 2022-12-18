package h09.sequence.operation;

import h09.sequence.Sequence;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Function;

public class FlatteningTransformingSequence<T, R> implements Sequence<R> {

    private final Sequence<T> sequence;
    private final Function<? super T, ? extends Sequence<? extends R>> function;

    public static <T, R> Function<Sequence<T>, Sequence<R>> of(Function<? super T, ? extends Sequence<? extends R>> function) {
        return sequence -> new FlatteningTransformingSequence<>(sequence, function);
    }

    public FlatteningTransformingSequence(Sequence<T> sequence, Function<? super T, ? extends Sequence<? extends R>> function) {
        this.sequence = sequence;
        this.function = function;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<>() {
            private final Iterator<T> iterator = sequence.iterator();
            private @Nullable Iterator<? extends R> currentIterator = null;

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
        };
    }
}
