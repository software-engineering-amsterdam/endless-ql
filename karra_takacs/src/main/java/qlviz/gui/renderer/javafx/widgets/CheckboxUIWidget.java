package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import qlviz.gui.viewModel.question.*;


public class CheckboxUIWidget implements UIWidget, QuestionViewModelVisitor {

    private final CheckBox node = new CheckBox();

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public void bindToQuestion(QuestionViewModel questionViewModel) {
        questionViewModel.accept(this);
    }

    @Override
    public void visit(BooleanQuestionViewModel booleanQuestion) {
        this.node.selectedProperty().bindBidirectional(booleanQuestion.valueProperty());
    }

    @Override
    public void visit(DateQuestionViewModel dateQuestion) {

    }

    @Override
    public void visit(DecimalQuestionViewModel decimalQuestion) {

    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestion) {

    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestion) {

    }

    @Override
    public void visit(StringQuestionViewModel stringQuestion) {

    }
}
