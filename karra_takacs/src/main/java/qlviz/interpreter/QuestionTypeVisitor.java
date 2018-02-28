package qlviz.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import qlviz.model.question.QuestionType;

public class QuestionTypeVisitor implements QuestionTypeTranslator {

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    @Override
    public QuestionType translate(TerminalNode node) {
        return QuestionType.valueOf(capitalize(node.getText()));
    }
}
