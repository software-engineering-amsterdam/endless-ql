import antlr.QLLexer;
import antlr.QLParser;
import model.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import visitor.VisitorForm;

import java.io.InputStream;

public class FormParser {

    public static Form parseForm(InputStream stream) throws IllegalArgumentException, UnsupportedOperationException{
        try{
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);

            // Walk it and attach our listener
            VisitorForm visitor = new VisitorForm();
            Form form = visitor.visit(parser.root());

            // Debug
            parser.reset();
//            Trees.inspect(parser.root(), parser);
            return form;
        } catch (Exception e){
            // TODO improve exception
            System.out.println("exception thrown during parsing");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}