package h09.operator;

import h09.basic.BasicBinaryOperations;

import java.util.function.BinaryOperator;

public class SumWithCoefficientsOperator<X, Y> implements BinaryOperator<X> {

    private final BasicBinaryOperations<X, Y> op;
    private final Y coeff1;
    private final Y coeff2;

    public SumWithCoefficientsOperator(BasicBinaryOperations<X, Y> op, Y coeff1, Y coeff2) {
        this.op = op;
        this.coeff1 = coeff1;
        this.coeff2 = coeff2;
    }

    @Override
    public X apply(X left, X right) {
        return op.add(op.mul(left, coeff1), op.mul(right, coeff2));
    }
}
