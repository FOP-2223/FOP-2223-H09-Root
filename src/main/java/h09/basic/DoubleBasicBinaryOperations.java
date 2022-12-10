package h09.basic;

public class DoubleBasicBinaryOperations implements BasicBinaryOperations<Double, Double> {

    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double mult(Double left, Double right) {
        return left * right;
    }
}
