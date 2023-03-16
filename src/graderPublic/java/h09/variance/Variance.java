package h09.variance;

import java.lang.reflect.Type;

/**
 * The variance of a type.
 */
public enum Variance {
    INVARIANT,
    COVARIANT,
    CONTRAVARIANT;

    /**
     * Returns a string representation of this variance and the given type.
     */
    public String toString(final Type type) {
        return switch (this) {
            case INVARIANT -> type.getTypeName();
            case COVARIANT -> "? extends " + type.getTypeName();
            case CONTRAVARIANT -> "? super " + type.getTypeName();
            default -> throw new AssertionError("Unexpected variance " + this);
        };
    }
}
