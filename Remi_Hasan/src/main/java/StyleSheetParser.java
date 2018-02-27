import model.StyleSheet;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import visitor.VisitorStyleSheet;

import java.io.IOException;
import java.io.InputStream;

public class StyleSheetParser {

    public static StyleSheet parseStyleSheet(InputStream stream) throws IOException, IllegalArgumentException, UnsupportedOperationException{
        try{
            // TODO
            QLSLexer lexer = new QLSLexer(CharStreams.fromStream(stream));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLSParser parser = new QLSParser(tokens);

            // Walk it and attach our listener
            VisitorStyleSheet visitor = new VisitorStyleSheet();
            StyleSheet styleSheet = visitor.visit(parser.root());

            // Debug
            parser.reset();
            Trees.inspect(parser.root(), parser);

            return styleSheet;
        } catch (Exception e){
            // TODO improve exception
            System.out.println("exception thrown during parsing");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}