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

    public StatementsEvaluator(final SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public final List<Question> evaluate(final Statements statements) {
        return statements.accept(this);
    }

    public final List<Question> visit(final Statements node) {
        final List<Question> questions = new ArrayList<>();
        for (final ASTNode statement : node.getStatementList()) {
            final List<Question> subQuestions = statement.accept(this);
            if (subQuestions != null)
                questions.addAll(subQuestions);
        }
        return questions;
    }

    public final List<Question> visit(final Question question) {
        return Collections.singletonList(question);
    }

    public final List<Question> visit(final IfStatement ifStatement) {
        return this.ifStatementEvaluator.evaluate(ifStatement, this.symbolTable);
    }
}