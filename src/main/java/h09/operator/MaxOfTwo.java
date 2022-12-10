package h09.operator;

import java.util.function.BinaryOperator;

public class MaxOfTwo<T extends Comparable<T>> implements BinaryOperator<T> {

    @Override
    public T apply(T left, T right) {
        return left.compareTo(right) > 0 ? left : right;
    }
}
