package h09.basic;

public class StringFactory implements BasicFactory<String> {

    private final String[] text = """
        Cupcake ipsum dolor sit amet
        Gummies cheesecake carrot cake cake tootsie roll
        Cotton candy bear claw bear claw jujubes wafer
        Ice cream liquorice bonbon tootsie roll jujubes cookie ice cream
        Gingerbread tiramisu chocolate cake candy jelly
        Tart sweet bear claw apple pie sugar plum candy canes cookie
        Icing jelly beans tootsie roll liquorice soufflé shortbread
        Brownie cheesecake powder topping wafer marshmallow icing cotton candy
        Macaroon cupcake candy toffee pastry chupa chups shortbread
        Toffee topping bonbon chocolate bar tootsie roll sugar plum halvah tiramisu
        Sugar plum croissant caramels gummi bears macaroon cupcake caramels fruitcake
        Tootsie roll topping cotton candy liquorice gummi bears dessert donut caramels
        Croissant ice cream jujubes sweet lemon drops gummies chupa chups brownie
        """.split("\\s+");

    private int current;

    public StringFactory(int start) {
        this.current = start;
    }

    @Override
    public String create() {
        return text[current += current == text.length - 1 ? 1 - text.length : 1];
    }
}
