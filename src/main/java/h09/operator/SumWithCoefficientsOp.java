package h09.operator;

import java.util.function.BinaryOperator;

public class SumWithCoefficientsOp<T extends Number> implements BinaryOperator<T> {

    private final T coeff_1;
    private final T coeff_2;

    public SumWithCoefficientsOp(T coeff_1, T coeff_2) {
        this.coeff_1 = coeff_1;
        this.coeff_2 = coeff_2;
    }

    @Override
    public T apply(T aDouble, T aDouble2) {
        return coeff_1 * aDouble + coeff_2 * aDouble2;
    }
}
