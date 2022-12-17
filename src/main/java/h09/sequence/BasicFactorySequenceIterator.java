package h09.sequence;

import h09.basic.BasicFactory;

import java.util.Iterator;

class BasicFactorySequenceIterator<T> implements Iterator<T> {
    private final BasicFactory<T> factory;

    public BasicFactorySequenceIterator(final BasicFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return factory.create();
    }
}
