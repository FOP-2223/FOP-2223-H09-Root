package h09.basic;

public class IntegerBasicBinaryOperations implements BasicBinaryOperations<Integer, Integer> {

    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer mult(Integer left, Integer right) {
        return left * right;
    }
}
