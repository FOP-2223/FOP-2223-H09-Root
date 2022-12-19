package h09.operator;

import java.util.function.BinaryOperator;

public class ComposedBinaryOperator<T> implements BinaryOperator<T> {

    private final BinaryOperator<T> op1;
    private final BinaryOperator<T> op2;
    private final BinaryOperator<T> op3;

    public ComposedBinaryOperator(BinaryOperator<T> op1, BinaryOperator<T> op2, BinaryOperator<T> op3) {
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    @Override
    public T apply(T left, T right) {
        // Apply first operator to given parameters
        T result1 = op1.apply(left, right);

        // Apply second operator to given parameters
        T result2 = op2.apply(left, right);

        // Return application of third operator to the intermediate results
        return op3.apply(result1, result2);
    }
}
