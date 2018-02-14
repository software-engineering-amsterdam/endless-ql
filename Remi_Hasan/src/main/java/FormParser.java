import model.Form;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

public class FormParser {

    public static Form parseForm(InputStream stream) {
        try {
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);

            // Walk it and attach our listener
            VisitorForm visitor = new VisitorForm();
            Form form = visitor.visit(parser.root());

            System.out.println(form);

            // Visualize tree
            parser.reset();
            Trees.inspect(parser.root(), parser);

            return form;

        } catch(IOException e){
            e.printStackTrace();
            System.exit(0);

            return new Form(null, null);
        }
    }
}
