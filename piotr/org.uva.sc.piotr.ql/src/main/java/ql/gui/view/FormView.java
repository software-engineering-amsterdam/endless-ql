package ql.gui.view;

import java.util.ArrayList;
import java.util.List;

public class FormView {
    private List<QuestionView> questionViews;

    public FormView() {
        this.questionViews = new ArrayList<>();
    }

    public void addQuestionView(QuestionView questionView) {
        this.questionViews.add(questionView);
    }

    public List<QuestionView> getQuestionViews() {
        return questionViews;
    }
}
