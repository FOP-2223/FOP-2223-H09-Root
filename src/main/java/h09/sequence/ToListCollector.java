package h09.sequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToListCollector<T> implements SequenceCollector<T, List<T>> {
    @Override
    public List<T> collect(Sequence<T> sequence) {
        final List<T> result = new ArrayList<>();
        final Iterator<T> iterator = sequence.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}