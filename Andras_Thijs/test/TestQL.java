import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import AST.*;

class TestQL {
    public void test() {
        FormReader formReader = new FormReader();

        CharStream testInput1 = CharStreams.fromString("");

        CharStream testInput2 = CharStreams.fromString("foo");

        CharStream testInput3 = CharStreams.fromString("form Box1HouseOwning {}");

        CharStream testInput4 = CharStreams.fromString("form Box1HouseOwning { foo }");
    }
}