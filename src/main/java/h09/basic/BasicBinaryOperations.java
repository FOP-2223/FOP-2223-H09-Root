package h09.basic;

public interface BasicBinaryOperations<X, Y> {
    X add(X left, X right);

    X mult(X left, Y right);
}
