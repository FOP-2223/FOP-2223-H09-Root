package h09.sequence;

import java.util.Iterator;

public class FibonacciSequenceIterator implements Iterator<Integer> {
    private int previous = 0;
    private int current = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int next = previous + current;
        previous = current;
        current = next;
        return next;
    }
}
