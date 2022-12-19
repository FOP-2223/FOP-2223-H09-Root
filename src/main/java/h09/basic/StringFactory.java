package h09.basic;

public class StringFactory implements BasicFactory<String> {

    private int current;
    private final String[] text;

    public StringFactory(int start, String[] text) {
        this.current = start;
        this.text = text;
    }

    @Override
    public String create() {
        return text[(current += current == text.length - 1 ? 1 - text.length : 1) - 1];
    }
}
