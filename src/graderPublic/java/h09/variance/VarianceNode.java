package h09.variance;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public record VarianceNode(Type expectedBound, Variance expectedVariance, VarianceNode... children) {
    public void check(final Type actualType, final boolean strict) {
        try {
            switch (expectedVariance) {
                case INVARIANT -> assertInvariantType(actualType);
                case COVARIANT -> assertCovariantType(actualType);
                case CONTRAVARIANT -> assertContravariantType(actualType);
            }
        } catch (AssertionFailedError e) {
            if (strict) {
                throw e;
            }
            final Type normalizedExpectedBound = normalizeType(expectedBound);
            Assertions.assertTrue(matchesInvariant(actualType, normalizedExpectedBound),
                "Type " + actualType + " does not match expected type "
                    + expectedVariance.toString(expectedBound) + " or raw type " + normalizedExpectedBound);
        }
        // now check the children recursively if this is a parameterized type
        if (expectedBound instanceof ParameterizedType expectedParameterizedType) {
            if (!(actualType instanceof ParameterizedType actualParameterizedType)) {
                throw new AssertionFailedError("Expected type " + expectedBound + " to be parameterized, but was not");
            }
            final Type[] actualTypeArguments = actualParameterizedType.getActualTypeArguments();
            final Type[] expectedTypeArguments = expectedParameterizedType.getActualTypeArguments();
            Assertions.assertEquals(expectedTypeArguments.length, actualTypeArguments.length,
                "Expected " + expectedTypeArguments.length + " type arguments, but got " + actualTypeArguments.length);
            for (int i = 0; i < expectedTypeArguments.length; i++) {
                final Type actualTypeArgument = actualTypeArguments[i];
                for (final VarianceNode child : children) {
                    child.check(actualTypeArgument, strict);
                }
            }
        }
    }

    private boolean matchesInvariant(final Type actualType, final Type normalizedExpectedBound) {
        if (actualType instanceof WildcardType) {
            return false;
        } else if (actualType instanceof ParameterizedType actualParameterizedType) {
            return normalizedExpectedBound.equals(actualParameterizedType.getRawType());
        } else {
            return normalizedExpectedBound.equals(actualType);
        }
    }

    private void assertInvariantType(final Type type) {
        if (type instanceof final WildcardType wildcardType) {
            if (wildcardType.getLowerBounds().length == 0) {
                Assertions.fail("Expected invariant type parameter, but found covariant wildcard type parameter " + wildcardType);
            } else {
                Assertions.fail("Expected invariant type parameter, but found contravariant wildcard type parameter " + wildcardType);
            }
        } else {
            Assertions.assertEquals(expectedBound, type,
                "Expected invariant type parameter" + expectedBound + ", but found " + type);
        }
    }

    private void assertCovariantType(final Type type) {
        if (!(type instanceof final WildcardType wildcardType)) {
            throw new AssertionFailedError("Expected covariant type parameter, but found invariant type parameter " + type);
        }
        final Type[] lowerBounds = wildcardType.getLowerBounds();
        final Type[] upperBounds = wildcardType.getUpperBounds();
        if (lowerBounds.length == 0) {
            final Type rawUpperBound = upperBounds[0] instanceof ParameterizedType parameterizedType
                ? parameterizedType.getRawType()
                : upperBounds[0];
            Assertions.assertEquals(expectedBound, rawUpperBound,
                "Expected covariant type parameter, but found wildcard type parameter with incorrect upper bound " + wildcardType);
        } else {
            Assertions.fail("Expected covariant type parameter, but found contravariant wildcard type parameter " + wildcardType);
        }
    }

    private void assertContravariantType(final Type type) {
        if (!(type instanceof final WildcardType wildcardType)) {
            throw new AssertionFailedError("Expected contravariant type parameter, but found invariant type parameter " + type);
        }
        final Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length == 0) {
            Assertions.fail("Expected contravariant type parameter, but found contravariant wildcard type parameter " + wildcardType);
        } else {
            final Type rawLowerBound = lowerBounds[0] instanceof ParameterizedType parameterizedType
                ? parameterizedType.getRawType()
                : lowerBounds[0];
            Assertions.assertEquals(expectedBound, rawLowerBound,
                "Expected contravariant type parameter, but found wildcard type parameter with incorrect lower bound " + wildcardType);
        }
    }

    private static Type normalizeType(final Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            return parameterizedType.getRawType();
        } else if (type instanceof WildcardType wildcardType) {
            final Type[] lowerBounds = wildcardType.getLowerBounds();
            final Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 0) {
                return upperBounds[0];
            } else {
                return lowerBounds[0];
            }
        } else {
            return type;
        }
    }
}
