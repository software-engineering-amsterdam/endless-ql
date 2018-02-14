import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class FormReader {

    public class FormReaderListener extends QLBaseListener {

        @Override
        public void enterForm(QLParser.FormContext ctx) {
            System.out.println(ctx.getText());
        }

    }

    private void parseForm (String path) throws IOException {

            CharStream charStream =  CharStreams.fromFileName(path);

            QLLexer lexer = new QLLexer(charStream);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            QLParser parser = new QLParser(tokens);

            QLParser.FormContext formContext = parser.form();

            ParseTreeWalker walker = new ParseTreeWalker();

            FormReaderListener listener = new FormReaderListener();

            walker.walk(listener, formContext);
    }

    private void parseForm (CharStream charStream){

        QLLexer lexer = new QLLexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);

        QLParser.FormContext formContext = parser.form();

        ParseTreeWalker walker = new ParseTreeWalker();

        FormReaderListener listener = new FormReaderListener();

        walker.walk(listener, formContext);
    }

}

