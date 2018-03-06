package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.numericExpressions.BinaryNumericOperationViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericLiteralViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericNegationViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericQuestionReferenceViewModel;
import qlviz.gui.viewModel.question.NumericQuestionViewModel;
import qlviz.model.numericExpressions.BinaryNumericOperation;
import qlviz.model.numericExpressions.NumericLiteral;
import qlviz.model.numericExpressions.NumericNegation;
import qlviz.model.question.NumericQuestion;
import qlviz.model.question.NumericQuestionReference;

import java.util.Map;

public class NumericExpressionViewModelLinker implements NumericExpressionViewModelVisitor {

    private final Map<String, NumericQuestionViewModel> questions;

    public NumericExpressionViewModelLinker(Map<String, NumericQuestionViewModel> questions) {
        this.questions = questions;
    }


    @Override
    public void visit(BinaryNumericOperationViewModel binaryNumericOperation) {
       binaryNumericOperation.getLeftSide().accept(this);
       binaryNumericOperation.getRightSide().accept(this);
    }

    @Override
    public void visit(NumericLiteralViewModel literal) {
        return;
    }

    @Override
    public void visit(NumericQuestionReferenceViewModel numericQuestionReference) {
        numericQuestionReference.setQuestion(this.questions.get(numericQuestionReference.getName()));
    }

    @Override
    public void visit(NumericNegationViewModel numericNegation) {
        numericNegation.getInnerExpression().accept(this);
    }
}
