import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;

public class Main {
    public static void main (String[] args){

        FormReader formReader = new FormReader();

        CharStream testInput1 = CharStreams.fromString("");

        CharStream testInput2 = CharStreams.fromString("foo");

        CharStream testInput3 = CharStreams.fromString("form Box1HouseOwning {}");

        CharStream testInput4 = CharStreams.fromString("form Box1HouseOwning { foo }");


        try {
            formReader.parseFile("src\\test_grammar.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
