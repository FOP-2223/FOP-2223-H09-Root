package h09.sequence;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class BasicFactorySequence/*TODO: H3.4*/ {
// TODO: H3.4 - uncomment if implemented
//    @Override
    public Iterator</*TODO: H3.4 replace*/Object> iterator() {
        return new Iterator<>() {
            // TODO: H3.4 - attributes here
            @Override
            public boolean hasNext() {
                return crash(); // TODO: H3.4 - remove if implemented
            }

            @Override
            public /*TODO: H3.3 replace*/Object next() {
                return crash(); // TODO: H3.4 - remove if implemented
            }
        };
    }
}
