package h09.sequence;

import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * A sequence of discrete values of {@link T}
 *
 * <p>
 * If this sequence is not a {@link Range}, it must, by definition, contain a cycle.
 * </p>
 */
@FunctionalInterface
public interface Sequence<T> {

    /**
     * Creates an iterator for this sequence.
     */
    Iterator<T> iterator();

    // TODO: Collect

    interface Factory<T> {
        Sequence<T> create(T... values);

        Sequence<T> create(Iterable<T> iterable);
    }

    static <T> Sequence<T> concat(Sequence<? extends T> a, Sequence<? extends T> b) {
        return () -> new Iterator<>() {
            Iterator<? extends T> it = a.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext() || (it = b.iterator()).hasNext();
            }

            @Override
            public T next() {
                return it.next();
            }
        };
    }
}
