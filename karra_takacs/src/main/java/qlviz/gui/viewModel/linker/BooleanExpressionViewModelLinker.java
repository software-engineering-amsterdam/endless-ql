package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.booleanExpressions.*;
import qlviz.gui.viewModel.question.BooleanQuestionViewModel;
import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BooleanLiteral;
import qlviz.model.booleanExpressions.Negation;
import qlviz.model.booleanExpressions.NumericComparison;
import qlviz.model.question.BooleanQuestion;
import qlviz.model.question.BooleanQuestionReference;

import java.util.Map;

public class BooleanExpressionViewModelLinker implements BooleanExpressionViewModelVisitor {

    private final Map<String, BooleanQuestionViewModel> questions;
    private final NumericExpressionViewModelLinker numericExpressionLinker;

    public BooleanExpressionViewModelLinker(Map<String, BooleanQuestionViewModel> questions, NumericExpressionViewModelLinker numericExpressionLinker) {
        this.questions = questions;
        this.numericExpressionLinker = numericExpressionLinker;
    }


    @Override
    public void visit(BinaryBooleanOperationViewModel binaryBooleanOperation) {
       binaryBooleanOperation.getLeftSide().accept(this);
       binaryBooleanOperation.getRightSide().accept(this);
    }

    public void visit(BooleanLiteralViewModel literal) {
        return;
    }

    public void visit(NegationViewModel negation) {
        negation.getOperand().accept(this);
    }

    public void visit(BooleanQuestionReferenceViewModel booleanQuestionReference) {
        booleanQuestionReference.setQuestion(this.questions.get(booleanQuestionReference.getQuestionName()));
    }

    @Override
    public void visit(NumericComparisonViewModel numericComparison) {
        return;
    }

    public void accept(NumericComparisonViewModel numericComparison) {
        numericComparison.getLeftSide().accept(this.numericExpressionLinker);
        numericComparison.getRightSide().accept(this.numericExpressionLinker);
    }
}
