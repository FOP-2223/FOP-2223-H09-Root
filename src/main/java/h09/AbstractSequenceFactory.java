package h09;

public class AbstractSequenceFactory<T> implements Sequence.Factory<T> {

    @Override
    public Sequence<T> create(T... values) {
        return new ArraySequence<>(values);
    }

    @Override
    public Sequence<T> create(Iterable<T> iterable) {
        return iterable::iterator;
    }
}
