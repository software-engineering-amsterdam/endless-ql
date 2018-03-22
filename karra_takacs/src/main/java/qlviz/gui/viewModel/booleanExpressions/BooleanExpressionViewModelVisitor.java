package qlviz.gui.viewModel.booleanExpressions;

import qlviz.model.question.BooleanQuestionReference;

public interface BooleanExpressionViewModelVisitor {
    void visit(BooleanLiteralViewModel literal);
    void visit(NegationViewModel negation);
    void visit(BooleanQuestionReferenceViewModel booleanQuestionReference);
    void visit(NumericComparisonViewModel numericComparison);
    void visit(BinaryBooleanOperationViewModel binaryBooleanOperationViewModel);
}
