package qlviz.gui.viewModel.question;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.model.question.BooleanQuestion;

public class BooleanQuestionViewModel extends BaseQuestionViewModel {

    private final BooleanQuestion question;
    private final BooleanProperty value;

    public BooleanQuestionViewModel(BooleanQuestion question) {
        super(question);
        this.question = question;
        this.value = new SimpleBooleanProperty();
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public BooleanProperty valueProperty() {
        return value;
    }
}



