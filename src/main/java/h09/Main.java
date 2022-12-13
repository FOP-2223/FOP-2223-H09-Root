package h09;

import h09.sequence.Sequence;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    void foo(Sequence<Foo> sequence) {
        for (Foo foo : sequence) {
            System.out.println(foo);
        }
    }
}
