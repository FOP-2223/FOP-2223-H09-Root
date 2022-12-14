package h09.sequence;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class BinaryOpFoldCollector<T> implements SequenceCollector<T, T> {

    private final T identity;
    private final BinaryOperator<T> operator;

    public BinaryOpFoldCollector(T identity, BinaryOperator<T> operator) {
        this.identity = identity;
        this.operator = operator;
    }

    @Override
    public T collect(Sequence<? extends T> sequence) {
        T result = identity;
        final Iterator<? extends T> iterator = sequence.iterator();
        while (iterator.hasNext()) {
            result = operator.apply(result, iterator.next());
        }
        return result;
    }
}
