import antlr.QLSLexer;
import antlr.QLSParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.stylesheet.StyleSheet;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import visitor.stylesheet.VisitorStyleSheet;

import java.io.IOException;
import java.io.InputStream;

public class StyleSheetParser {

    // TODO rename StyleSheetParser and FormParser to ParserStyleSheet and ParserForm for consistency

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
//            Trees.inspect(parser.root(), parser);

            // Debug: print object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(styleSheet));

            return styleSheet;
        } catch (Exception e){
            // TODO improve exception
            System.out.println("exception thrown during parsing");
            e.printStackTrace();
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}