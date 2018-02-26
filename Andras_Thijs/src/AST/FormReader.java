package AST;

import Nodes.*;
import AST.gen.*;
import com.sun.istack.internal.NotNull;
import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FormReader {

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


    public QLForm parseFile (String path) throws IOException {

        CharStream charStream = CharStreams.fromFileName(path);

        return parseCharstream(charStream);

    }

    public QLForm parseString (String s) {

        CharStream charStream = CharStreams.fromString(s);

        return parseCharstream(charStream);

    }

    public QLForm parseCharstream (CharStream charStream){


        QLLexer lexer = new QLLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        FormVisitor formVisitor = new FormVisitor();

        QLForm traverseResult = (QLForm) formVisitor.visit(parser.form());

        return traverseResult;

    }

    private static class FormVisitor extends QLBaseVisitor<QLForm> {

        @Override
        public QLForm visitForm(@NotNull QLParser.FormContext ctx) {
            String formName = ctx.VARIABLE().getText();
            QuestionVisitor questionVisitor = new QuestionVisitor();
            List<Question> questions = ctx.question()
                    .stream()
                    .map(question -> question.accept(questionVisitor))
                    .collect(toList());
            return new QLForm(formName, questions);
        }
    }

    private static class QuestionVisitor extends QLBaseVisitor<Question> {

        @Override
        public Question visitQuestion(@NotNull QLParser.QuestionContext ctx) {
            String questionName = ctx.VARIABLE().getText();
            String questionLabel = ctx.STRING().getText();
            String questionType = ctx.TYPE().getText();

            return new Question(questionName, questionLabel, questionType);
        }
    }
}
