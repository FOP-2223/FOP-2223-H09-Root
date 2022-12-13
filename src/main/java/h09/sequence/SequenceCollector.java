package h09.sequence;

public interface SequenceCollector<T, R> {
    R collect(Sequence<T> sequence);
}
