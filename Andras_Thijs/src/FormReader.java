import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class FormReader {

    public class FormReaderListener extends QLBaseListener {

        @Override
        public void enterForm(QLParser.FormContext ctx) {
            //System.out.println(ctx.getText());
        }

        @Override
        public void enterQuestion(QLParser.QuestionContext ctx){
            System.out.println(ctx.getText());
        }

    }

    public class FormReaderVisitor extends QLBaseVisitor<Object> {


        @Override
        public Object visitForm(QLParser.FormContext ctx) {
            return visitChildren(ctx);
        }

        @Override
        public Object visitQuestion(QLParser.QuestionContext ctx) {
            return visitChildren(ctx);
        }

    }

    public static class SyntaxErrorListener extends BaseErrorListener {
        public static SyntaxErrorListener INSTANCE = new SyntaxErrorListener();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine,
                                String msg, RecognitionException e)
        {
            String sourceName = recognizer.getInputStream().getSourceName();
            if (!sourceName.isEmpty()) {
                sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
            }

            System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
        }
    }

    public void parseFile (String path) throws IOException {

        CharStream charStream = CharStreams.fromFileName(path);

        parseCharstream(charStream);

    }

    public void parseString (String s) {

        CharStream charStream = CharStreams.fromString(s);

        parseCharstream(charStream);

    }

    public void parseCharstream (CharStream charStream){


        QLLexer lexer = new QLLexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);

        QLParser.FormContext formContext = parser.form();

        ParseTreeWalker walker = new ParseTreeWalker();

        FormReaderListener listener = new FormReaderListener();

    }

}

