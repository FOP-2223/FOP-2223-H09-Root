package h09.math;

import h09.Sequence;

import java.util.Iterator;

public class FibonacciSequence implements Sequence<Integer> {

    private final int startIndex;

    public FibonacciSequence(int startIndex) {
        this.startIndex = startIndex;
    }

    public FibonacciSequence() {
        this(0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index = startIndex;
            private int previous = 0;
            private int current = 1;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                if (index == 0) {
                    index++;
                    return 0;
                } else if (index == 1) {
                    index++;
                    return 1;
                } else {
                    int next = previous + current;
                    previous = current;
                    current = next;
                    index++;
                    return next;
                }
            }
        };
    }
}
