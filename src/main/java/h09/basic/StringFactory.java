package h09.basic;

public class StringFactory implements BasicFactory<String> {

    private final String[] text;

    private int current;

    public StringFactory(int start, String[] text) {
        this.current = start;
        this.text = text;
    }

    @Override
    public String create() {
        return text[current = (current + 1) % text.length];
    }
}
