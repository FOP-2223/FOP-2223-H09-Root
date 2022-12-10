package h09;

import java.util.Comparator;
import java.util.function.Supplier;

public interface Range<T> extends Sequence<T> {

    Supplier<? extends T> getLow();

    Supplier<? extends T> getHigh();

    Comparator<? super T> getComparator();

    interface ComparableFactory<T extends Comparable<? super T>> {
        Range<T> create(T low, T high, T step);
    }

    interface Factory<T> {
        Range<T> create(T low, T high, T step, Comparator<? super T> comparator);
    }
}
