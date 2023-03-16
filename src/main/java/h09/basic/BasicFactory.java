package h09.basic;

/**
 * A basic factory is a function that creates a new instance of {@link T}.
 */
public interface BasicFactory<T> {

    /**
     * Create a new instance of {@link T}.
     *
     * @return a new instance of {@link T}
     */
    T create();
}
