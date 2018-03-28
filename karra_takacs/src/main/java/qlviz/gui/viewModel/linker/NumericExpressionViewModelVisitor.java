package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.numericExpressions.BinaryNumericOperationViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericLiteralViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericNegationViewModel;
import qlviz.gui.viewModel.numericExpressions.NumericQuestionReferenceViewModel;

public interface NumericExpressionViewModelVisitor {
    void visit(BinaryNumericOperationViewModel binaryNumericOperation);
    void visit(NumericLiteralViewModel numericLiteral);
    void visit(NumericNegationViewModel numericNegation);
    void visit(NumericQuestionReferenceViewModel numericQuestionReference);
}


