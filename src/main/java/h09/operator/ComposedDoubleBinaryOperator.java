package h09.operator;

import java.util.function.BinaryOperator;

public class ComposedDoubleBinaryOperator implements BinaryOperator<Double> {

    private final BinaryOperator<Double> op1;
    private final BinaryOperator<Double> op2;
    private final BinaryOperator<Double> op3;

    public ComposedDoubleBinaryOperator(BinaryOperator<Double> op1, BinaryOperator<Double> op2, BinaryOperator<Double> op3) {
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    @Override
    public Double apply(Double left, Double right) {
        // Apply first operator to given parameters
        Double result1 = op1.apply(left, right);

        // Apply second operator to given parameters
        Double result2 = op2.apply(left, right);

        // Return application of third operator to the intermediate results
        return op3.apply(result1, result2);
    }
}
