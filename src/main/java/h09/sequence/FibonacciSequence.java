package h09.sequence;

import java.util.Iterator;

public class FibonacciSequence implements Sequence<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
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
                return current;
            }
        };
    }
}
