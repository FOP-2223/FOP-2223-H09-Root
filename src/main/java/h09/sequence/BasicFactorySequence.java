package h09.sequence;

import h09.basic.BasicFactory;

import java.util.Iterator;

public class BasicFactorySequence<T> implements Sequence<T> {

    private final BasicFactory<T> factory;

    public BasicFactorySequence(BasicFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicFactorySequenceIterator();
    }

    private class BasicFactorySequenceIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            return factory.create();
        }
    }
}
