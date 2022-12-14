package h09.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class ToListCollector<T> implements SequenceCollector<T, List<T>> {

    private final Supplier<List<T>> listSupplier;

    public ToListCollector(Supplier<List<T>> listSupplier) {
        this.listSupplier = listSupplier;
    }

    public ToListCollector() {
        this(ArrayList::new);
    }

    @Override
    public List<T> collect(Sequence<T> sequence) {
        final List<T> result = listSupplier.get();
        final Iterator<T> iterator = sequence.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}
