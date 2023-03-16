package h09;

import java.util.Random;

public class RandomExtensions {

    /**
     * Generates a random string of length 2 to 8.
     */
    public static String generateString(final Random random, final int num) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2 + num % 7; i++) {
            builder.append((char) random.nextInt('a', 'z'));
        }
        return builder.toString();
    }
}
