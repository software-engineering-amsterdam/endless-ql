package AST;

import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

class FormReaderTest {

    @Test
    void parseCharStream() {
        String input = "form OrderOfOperations {\n" +
            "\ttest1: \"test1\" integer(3 * 2 + 7 * 2)\n" +
            "}\n";

        FormReader formReader = new FormReader();

        assert formReader.parseCharStream(CharStreams.fromString(input)) != null;
    }
}
