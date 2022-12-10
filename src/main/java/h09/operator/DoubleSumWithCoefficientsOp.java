package h09.operator;

import java.util.function.BinaryOperator;

public class DoubleSumWithCoefficientsOp implements BinaryOperator<Double> {

    private final double coeff_1;
    private final double coeff_2;

    public DoubleSumWithCoefficientsOp(double coeff_1, double coeff_2) {
        this.coeff_1 = coeff_1;
        this.coeff_2 = coeff_2;
    }

    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        return coeff_1 * aDouble + coeff_2 * aDouble2;
    }
}
