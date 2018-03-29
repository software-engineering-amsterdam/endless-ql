package qlviz.gui.viewModel.question;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelFactory;
import qlviz.model.expressions.BooleanExpressionGetter;
import qlviz.model.expressions.NumericExpressionGetter;
import qlviz.model.question.BooleanQuestion;

import java.util.List;

public class BooleanQuestionViewModel extends BaseQuestionViewModel {

    private final BooleanQuestion question;
    private final BooleanProperty value;
    private final BooleanExpressionViewModel expression;

    public BooleanQuestionViewModel(
            BooleanQuestion question,
            List<BooleanExpressionViewModel> conditions,
            BooleanExpressionViewModelFactory viewModelFactory) {
        super(question, conditions);
        this.question = question;
        this.value = new SimpleBooleanProperty();
        if (question.getValueExpression() != null) {
            this.expression = viewModelFactory.create(
                    question.getValueExpression().accept(new BooleanExpressionGetter()));
            this.value.bind(this.expression.valueProperty());
        }
        else
        {
            this.expression = null;
        }
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



