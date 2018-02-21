package main.org.uva.ql.validation;

import main.org.uva.ql.ast.Conditional;
import main.org.uva.ql.ast.Form;
import main.org.uva.ql.ast.Question;

import main.org.uva.ql.ast.Statement;
import main.org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker implements StatementVisitor<Void, String> {

    private SymbolTable table;
    private List<Question> questions;

    public TypeChecker() {
        this.table = new SymbolTable();
        this.questions = new ArrayList<>();
    }

    public void execute (Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        for (Question question : this.questions) {
            System.out.println(question);
            this.table.add(question.getName(), question.getType());
        }

        System.out.println(table.size());
    }

    @Override
    public Void visit(Question question, String context) {
        this.questions.add(question);

        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {

        for (Statement statement : conditional.getIfSide()) {
            statement.accept(this, null);
        }

        for (Statement statement : conditional.getElseSide()) {
            statement.accept(this, null);
        }

        return null;
    }
}
