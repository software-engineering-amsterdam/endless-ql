package qlviz.gui.renderer.javafx.widgets;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.*;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownUIWidget extends ControlUIWidget<ComboBox<String>> implements QuestionViewModelVisitor, ParameterVisitor {


    public DropdownUIWidget(List<Parameter> parameters) {
        this.node = new ComboBox<>();
        for (Parameter parameter : parameters) {
            parameter.accept(this);
        }
    }

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
        stringQuestion.valueProperty().bindBidirectional(this.node.valueProperty());
    }

    @Override
    public void visit(StringParameter stringParameter) {
        this.node.itemsProperty().get().add(stringParameter.getValue());
    }

    @Override
    public void visit(NumericParameter numericParameter) {

    }

    @Override
    public void visit(ColorParameter colorParameter) {

    }
}
