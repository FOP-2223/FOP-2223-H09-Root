package h09.basic;

public class StringBasicBinaryOperations implements BasicBinaryOperations<String, Integer> {

    @Override
    public String add(String left, String right) {
        return left + right;
    }

    @Override
    public String mult(String left, Integer right) {
        return left.repeat(right);
    }
}
