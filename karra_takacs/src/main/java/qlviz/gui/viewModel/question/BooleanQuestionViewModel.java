package qlviz.gui.viewModel.question;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.question.BooleanQuestion;

import java.util.List;

public class BooleanQuestionViewModel extends BaseQuestionViewModel {

    private final BooleanQuestion question;
    private final BooleanProperty value;

    public BooleanQuestionViewModel(BooleanQuestion question, List<BooleanExpressionViewModel> conditons) {
        super(question, conditons);
        this.question = question;
        this.value = new SimpleBooleanProperty();
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedQuestionViewModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public BooleanProperty valueProperty() {
        return value;
    }
}



