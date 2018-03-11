package qlviz.gui.renderer.javafx;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import qlviz.gui.viewModel.question.*;

import java.math.BigDecimal;

public class SpinboxUIWidget implements UIWidget, QuestionViewModelVisitor {

    private final Spinner<BigDecimal> spinner = new Spinner<>();

    @Override
    public Node getNode() {
        return spinner;
    }

    @Override
    public void bindToQuestion(QuestionViewModel questionViewModel) {
        questionViewModel.accept(this);
    }

    @Override
    public void visit(BooleanQuestionViewModel booleanQuestion) {
        return;
    }

    @Override
    public void visit(DateQuestionViewModel dateQuestion) {
        return;
    }

    @Override
    public void visit(DecimalQuestionViewModel decimalQuestion) {
        if (decimalQuestion.getValueExpression() != null){
            decimalQuestion.valueProperty().bind(this.spinner.valueProperty());
        }
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {
        if (integerQuestion.getValueExpression() != null) {
            integerQuestion.valueProperty().bind(this.spinner.valueProperty());
        }
    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {
        if (moneyQuestion.getValueExpression() != null) {
            moneyQuestion.valueProperty().bind(this.spinner.valueProperty());
        }
    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {
        return;
    }
}
