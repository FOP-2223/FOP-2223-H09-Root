package h09.sequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class BasicSequenceTest {

    @Test
    void onEach() {
        final int[] counter = {0};
        Sequence.of(2, 4, 6)
            .then(OnEachSequence.of(s -> counter[0]++))
            .collect(VoidCollector.get());
        Assertions.assertEquals(3, counter[0]);
    }

    @Test
    void limit() {
        final int[] counter = {0};
        Sequence.of(5, 6, 7, 8, 1, 2)
            .then(LimitSequence.of(4))
            .then(OnEachSequence.of(s -> counter[0]++))
            .collect(VoidCollector.get());
        Assertions.assertEquals(4, counter[0]);
    }

    @Test
    void all() {

        Sequence<String> seq0 = Sequence.of("Generics", "sind", "nicht", "toll", "und", "super");
        Sequence<String> seq1 = new FilteringSequence<>(seq0, s -> !s.equals("nicht"));
        Sequence<String> seq2 = new TransformingSequence<>(seq1, String::toUpperCase);
        Sequence<String> seq3 = new TransformingSequence<>(seq2, s -> s + "!");
        Sequence<String> seq4 = new LimitSequence<>(seq3, 3);

        Iterator<String> it = seq4.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Alternative with sequence.then

        Sequence<String> seq = Sequence.of("Generics", "sind", "nicht", "toll")
            .then(FilteringSequence.of(s -> !s.equals("nicht")))
            .then(TransformingSequence.of(String::toUpperCase))
            .then(TransformingSequence.of(s -> s + "!"))
            .then(LimitSequence.of(3));
//
//        Iterator<String> it1 = seq.iterator();
//        System.out.println(it1.next()); // GERNERICS!
//        System.out.println(it1.next()); // SIND!
//        System.out.println(it1.next()); // TOLL!

//        Sequence.of("Hello", "World", "!")
//            .then(FlatteningTransformingSequence.of(s -> PrimitiveSequence.of(s.toCharArray())))
//            .then(FilteringSequence.of(Character::isLowerCase))
//            .then(OnEachSequence.of(System.out::println))
//            .then(LimitSequence.of(2))
//            .collect(VoidCollector.get());
    }
}
