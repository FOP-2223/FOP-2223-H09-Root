package h09.sequence;

import java.util.Comparator;

public interface Range<T> extends Sequence<T> {

    T getLow();

    T getHigh();

    Comparator<? super T> getComparator();

    static <T extends Comparable<? super T>> Range<T> create(Sequence<T> sequence) {
        throw new UnsupportedOperationException("TODO");
    }

    static <T> Range<T> create(Sequence<T> sequence, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("TODO");
    }
}
