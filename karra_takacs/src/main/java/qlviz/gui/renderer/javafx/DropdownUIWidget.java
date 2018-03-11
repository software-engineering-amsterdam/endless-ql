package qlviz.gui.renderer.javafx;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.Parameter;

import java.util.List;

public class DropdownUIWidget implements UIWidget, QuestionViewModelVisitor {

    private final ComboBox<String> comboBox = new ComboBox<>();

    public DropdownUIWidget(List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            parameter.
        }
    }

    @Override
    public Node getNode() {
        return this.comboBox;
    }

    @Override
    public void bindToQuestion(QuestionViewModel questionViewModel) {

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

    }
}
