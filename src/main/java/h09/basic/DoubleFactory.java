package h09.basic;

public class DoubleFactory implements BasicFactory<Double> {

    private double current;
    private final double step;

    public DoubleFactory(double start, double step) {
        this.current = start;
        this.step = step;
    }

    @Override
    public Double create() {
        return current += step;
    }
}
