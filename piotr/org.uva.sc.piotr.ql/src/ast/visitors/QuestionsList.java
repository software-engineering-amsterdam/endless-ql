package ast.visitors;

import ast.model.statement.Question;

import java.util.ArrayList;
import java.util.Objects;

public class QuestionsList extends ASTNodeAbstractVisitor {

    private ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public void visit(Question question) {
        this.questions.add(question);
        super.visit(question);
    }

    public void validateDuplicates() throws RuntimeException {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getVariableName(), question2.getVariableName())
                        && !Objects.equals(question1.getVariableType().getIdentifier(), question2.getVariableType().getIdentifier())
                        ) {
                    throw new RuntimeException("Duplicate question declarations with different types in lines : "
                            + question1.getStartLine()
                            + "-"
                            + question1.getEndLine()
                            + " and "
                            + question2.getStartLine()
                            + "-"
                            + question2.getEndLine()
                    );
                }
            }
        }
    }

    public void validateLabels() throws Exception {
        for (Question question1 : questions) {
            for (Question question2 : questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getLabel(), question2.getLabel())) {
                    throw new Exception("Duplicate label declarations in lines : "
                            + question1.getStartLine()
                            + " and "
                            + question2.getStartLine()
                    );
                }
            }
        }
    }
}
