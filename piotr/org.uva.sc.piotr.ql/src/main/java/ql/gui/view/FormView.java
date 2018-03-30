package ql.gui.view;

import java.util.ArrayList;
import java.util.List;

public class FormView {
    private final List<QuestionView> questionViews;

    public FormView() {
        this.questionViews = new ArrayList<>();
    }

    public void addQuestionView(QuestionView questionView) {
        questionView.refreshVisibility();
        this.questionViews.add(questionView);
    }

    public List<QuestionView> getQuestionViews() {
        return questionViews;
    }
}
