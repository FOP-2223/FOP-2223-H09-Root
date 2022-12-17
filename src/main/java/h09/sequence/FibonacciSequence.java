package h09.sequence;

import java.util.Iterator;

public class FibonacciSequence implements Sequence<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciSequenceIterator();
    }
}
