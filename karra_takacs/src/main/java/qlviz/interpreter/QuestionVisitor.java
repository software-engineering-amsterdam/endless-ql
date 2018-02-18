package qlviz.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.*;

public class QuestionVisitor extends QLBaseVisitor<Question> {

    private final QuestionTypeTranslator questionTypeTranslator;

    public QuestionVisitor(QuestionTypeTranslator questionTypeTranslator) {
        this.questionTypeTranslator = questionTypeTranslator;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        QuestionType type =
                questionTypeTranslator.translate(ctx.TYPE());
        String text = ctx.questionText().getText();
        String name = ctx.questionName().getText();
        switch (type){
            case Boolean:
                return new BooleanQuestion(name, text, type);
            case Money:
                return new MoneyQuestion(name, text, type);
            case String:
                return new StringQuestion(name, text, type);
            case Integer:
                return new IntegerQuestion(name, text, type);
            case Date:
                return new DateQuestion(name, text, type);
            case Decimal:
                return new DecimalQuestion(name, text, type);
        }
        return null;
    }
}
