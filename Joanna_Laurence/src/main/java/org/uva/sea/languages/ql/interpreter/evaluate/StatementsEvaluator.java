package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.IfStatement;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.Statements;
import org.uva.sea.languages.ql.parser.visitor.BaseASTVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatementsEvaluator extends BaseASTVisitor<List<Question>> {

    private final SymbolTable symbolTable;

    private final IfStatementEvaluator ifStatementEvaluator = new IfStatementEvaluator();

    public StatementsEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<Question> evaluate(Statements statements) {
        return statements.accept(this);
    }

    public List<Question> visit(Statements node) {
        List<Question> questions = new ArrayList<>();
        for (ASTNode statement : node.getStatementList()) {
            List<Question> subQuestions = statement.accept(this);
            if (subQuestions != null)
                questions.addAll(subQuestions);
        }
        return questions;
    }

    public List<Question> visit(Question question) {
        return Collections.singletonList(question);
    }

    public List<Question> visit(IfStatement ifStatement) {
        return this.ifStatementEvaluator.evaluate(ifStatement, this.symbolTable);
    }
}