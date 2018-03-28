package ql.typechecker;

import ql.ast.Form;
import ql.ast.statements.*;
import ql.typechecker.messages.Messages;
import ql.typechecker.messages.error.DuplicateDeclaration;
import ql.typechecker.messages.warning.DuplicateLabel;
import ql.visitors.FormVisitor;
import ql.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionVisitor implements FormVisitor, StatementVisitor<Void> {
    private final Messages messages;
    private final List<String> questionLabels;
    private final DeclarationTable declarations;

    public QuestionVisitor(Messages messages) {
        this.messages = messages;
        this.questionLabels = new ArrayList<>();
        this.declarations = new DeclarationTable();
    }

    @Override
    public void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
    }

    @Override
    public Void visit(CalculableQuestion calculableQuestion) {
        checkRedeclaration(calculableQuestion);
        checkLabelDuplication(calculableQuestion);
        return null;
    }

    @Override
    public Void visit(IfThen ifThen) {
        for (Statement statement : ifThen.getThenStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfThenElse ifThenElse) {
        for (Statement statement : ifThenElse.getThenStatements()) {
            statement.accept(this);
        }
        for (Statement statement : ifThenElse.getElseStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question question) {
        checkRedeclaration(question);
        checkLabelDuplication(question);
        return null;
    }

    private void checkRedeclaration(Question question) {
        if (declarations.exists(question.getIdentifier())) {
            messages.addError(new DuplicateDeclaration(question.getLineNumber(), question.getIdentifier().toString()));
        }
        declarations.add(question.getIdentifier(), question.getType());
    }

    private void checkLabelDuplication(Question question) {
        if (questionLabels.contains(question.getDescription().getValue())) {
            messages.addWarning(new DuplicateLabel(question.getLineNumber(), question.getDescription().getValue()));
        }
        questionLabels.add(question.getDescription().getValue());
    }
}
