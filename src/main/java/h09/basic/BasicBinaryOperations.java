package h09.basic;

/**
 * A basic interface for binary operations.
 */
public interface BasicBinaryOperations<X, Y> {
    X add(X left, X right);

    X mul(X left, Y right);
}
