package h09.operator;

import java.util.function.BinaryOperator;

public class ComposedBinaryOperator<T> implements BinaryOperator<T> {

    private final BinaryOperator<T> op_1;
    private final BinaryOperator<T> op_2;
    private final BinaryOperator<T> op_3;

    public ComposedBinaryOperator(BinaryOperator<T> op_1, BinaryOperator<T> op_2, BinaryOperator<T> op_3) {
        this.op_1 = op_1;
        this.op_2 = op_2;
        this.op_3 = op_3;
    }

    @Override
    public T apply(T left, T right) {
        // Apply first operator to given parameters
        T result1 = op_1.apply(left, right);

        // Apply second operator to given parameters
        T result2 = op_2.apply(left, right);

        // Return application of third operator to the intermediate results
        return op_3.apply(result1, result2);
    }
}
