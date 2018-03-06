package ast.visitors.filters;

import ast.model.statements.Question;
import ast.visitors.ASTNodeAbstractVisitor;

import java.util.ArrayList;

public class QuestionsFilter extends ASTNodeAbstractVisitor {

    private ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public void visit(Question question) {
        this.questions.add(question);
        super.visit(question);
    }
}
