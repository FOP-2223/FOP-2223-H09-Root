package h09;

import java.util.Comparator;

public interface RangeConverter {
    <T extends Comparable<? super T>> Range<T> convert(Sequence<T> sequence);

    <T> Range<T> convert(Sequence<T> sequence, Comparator<? super T> comparator);
}
