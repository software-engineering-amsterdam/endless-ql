package qlviz.gui.viewModel.booleanExpressions;

import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactory;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.booleanExpressions.*;
import qlviz.model.question.BooleanQuestionReference;

public class BooleanExpressionViewModelFactoryImpl implements BooleanExpressionViewModelFactory,
        TypedBooleanExpressionVisitor<BooleanExpressionViewModel> {

    private final NumericExpressionViewModelFactory numericExpressionViewModelFactory;

    public BooleanExpressionViewModelFactoryImpl(NumericExpressionViewModelFactory numericExpressionViewModelFactory) {
        this.numericExpressionViewModelFactory = numericExpressionViewModelFactory;
    }

    @Override
    public BooleanExpressionViewModel create(BooleanExpression booleanExpression) {
        return booleanExpression.accept(this);
    }

    @Override
    public BooleanExpressionViewModel visit(BinaryBooleanOperation binaryBooleanOperation) {
        return new BinaryBooleanOperationViewModel(
                binaryBooleanOperation.getLeftSide().accept(this),
                binaryBooleanOperation.getRightSide().accept(this),
                binaryBooleanOperation.getOperator()
        );
    }

    @Override
    public BooleanExpressionViewModel visit(BooleanLiteral literal) {
        return new BooleanLiteralViewModel(literal.evaluate());
    }

    @Override
    public BooleanExpressionViewModel visit(Negation negation) {
        return new NegationViewModel(negation.accept(this));
    }

    @Override
    public BooleanExpressionViewModel visit(BooleanQuestionReference booleanQuestionReference) {
        return new BooleanQuestionReferenceViewModel(booleanQuestionReference);
    }

    @Override
    public BooleanExpressionViewModel visit(NumericComparison numericComparison) {
        return new NumericComparisonViewModel(
                this.numericExpressionViewModelFactory.create(numericComparison.getLeftSide()),
                this.numericExpressionViewModelFactory.create(numericComparison.getRightSide()),
                numericComparison.getOperator()
        );
    }
}
