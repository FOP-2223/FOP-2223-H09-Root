package h09.operator;

import java.util.function.BinaryOperator;

public class ComposedDoubleBinaryOperator implements BinaryOperator<Double> {

    private final BinaryOperator<Double> op_1;
    private final BinaryOperator<Double> op_2;
    private final BinaryOperator<Double> op_3;

    public ComposedDoubleBinaryOperator(BinaryOperator<Double> op_1, BinaryOperator<Double> op_2, BinaryOperator<Double> op_3) {
        this.op_1 = op_1;
        this.op_2 = op_2;
        this.op_3 = op_3;
    }

    @Override
    public Double apply(Double left, Double right) {
        // Apply first operator to given parameters
        Double result1 = op_1.apply(left, right);

        // Apply second operator to given parameters
        Double result2 = op_2.apply(left, right);

        // Return application of third operator to the intermediate results
        return op_3.apply(result1, result2);
    }
}
