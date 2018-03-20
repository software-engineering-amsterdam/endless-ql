package nl.uva.js.qlparser.logic;

import lombok.SneakyThrows;
import nl.uva.js.qlparser.antlr.QLSLexer;
import nl.uva.js.qlparser.antlr.QLSParser;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StylesheetBuilder {

    @SneakyThrows(IOException.class)
    public static Stylesheet parseStylesheetFromLocation(String location) {
        String content = new String(Files.readAllBytes(Paths.get(location)));
        return parseStylesheetFromString(content);
    }

    public static Stylesheet parseStylesheetFromString(String qlsInput) {
        ErrorListener errorListener = new ErrorListener();

        QLSLexer lexer = new QLSLexer(CharStreams.fromString(qlsInput));
        lexer.addErrorListener(errorListener);

        QLSParser parser = new QLSParser(new CommonTokenStream(lexer));
        parser.addErrorListener(errorListener);

        Stylesheet stylesheet = new QLSVisitor().visitStylesheet(parser.stylesheet());
        stylesheet.checkType();

        return stylesheet;
    }
}
