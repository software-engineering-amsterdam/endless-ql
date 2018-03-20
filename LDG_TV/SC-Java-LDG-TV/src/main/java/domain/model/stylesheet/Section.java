package domain.model.stylesheet;

import domain.model.ast.QuestionASTNode;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String label;
    private List<QuestionASTNode> questions;

    public Section(String label) {
        this.label = label;
        this.questions = new ArrayList<>();
    }

    /**
     * Adds a QuestionASTNode to the questionNodes list.
     * @param q QuestionASTNode to add
     */
    public void addQuestion(QuestionASTNode q) {
        this.questions.add(q);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<QuestionASTNode> getQuestions() {
        return questions;
    }

}
