package h09.sequence;

import java.util.Iterator;

public interface PrimitiveSequence {

    static Sequence<Integer> of(int... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Integer next() {
                return values[index++];
            }
        };
    }

    static Sequence<Long> of(long... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Long next() {
                return values[index++];
            }
        };
    }

    static Sequence<Double> of(double... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Double next() {
                return values[index++];
            }
        };
    }

    static Sequence<Float> of(float... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Float next() {
                return values[index++];
            }
        };
    }

    static Sequence<Character> of(char... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Character next() {
                return values[index++];
            }
        };
    }

    static Sequence<Byte> of(byte... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Byte next() {
                return values[index++];
            }
        };
    }

    static Sequence<Short> of(short... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Short next() {
                return values[index++];
            }
        };
    }

    static Sequence<Boolean> of(boolean... values) {
        return () -> new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public Boolean next() {
                return values[index++];
            }
        };
    }
}
