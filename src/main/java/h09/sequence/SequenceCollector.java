package h09.sequence;

@FunctionalInterface
public interface SequenceCollector<T, R> {
    R collect(Sequence<? extends T> sequence);
}
