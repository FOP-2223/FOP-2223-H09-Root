package h09.operator;

import h09.basic.BasicBinaryOperations;

import java.util.function.BinaryOperator;

public class SumWithCoefficientsOperator<X, Y> implements BinaryOperator<X> {

    private final Y coeff_1;
    private final Y coeff_2;

    private final BasicBinaryOperations<X, Y> op;

    public SumWithCoefficientsOperator(BasicBinaryOperations<X, Y> op, Y leftCoeff, Y rightCoeff) {
        this.op = op;
        this.coeff_1 = leftCoeff;
        this.coeff_2 = rightCoeff;
    }

    @Override
    public X apply(X left, X right) {
        return op.add(op.mul(left, coeff_1), op.mul(right, coeff_2));
    }
}
