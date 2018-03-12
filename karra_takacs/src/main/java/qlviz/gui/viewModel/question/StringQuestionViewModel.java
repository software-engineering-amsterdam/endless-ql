package qlviz.gui.viewModel.question;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.model.question.StringQuestion;

import java.util.List;

public class StringQuestionViewModel extends BaseQuestionViewModel {


    private final StringQuestion question;
    private final StringProperty value;

    public StringQuestionViewModel(StringQuestion question, List<BooleanExpressionViewModel> conditions) {
        super(question, conditions);
        this.question = question;
        this.value = new SimpleStringProperty();
    }

    @Override
    public void accept(QuestionViewModelVisitor visitor) {
        visitor.visit(this);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }
}
