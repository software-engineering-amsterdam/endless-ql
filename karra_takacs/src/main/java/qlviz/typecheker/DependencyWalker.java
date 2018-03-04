package qlviz.typecheker;

import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BooleanLiteral;
import qlviz.model.booleanExpressions.Negation;
import qlviz.model.booleanExpressions.NumericComparison;
import qlviz.model.numericExpressions.BinaryNumericOperation;
import qlviz.model.numericExpressions.NumericLiteral;
import qlviz.model.numericExpressions.NumericNegation;
import qlviz.model.question.*;

import java.util.ArrayList;
import java.util.List;

public class DependencyWalker implements
        TypedNumericExpressionVisitor<List<Question>>,
        TypedBooleanExpressionVisitor<List<Question>>,
        QuestionVisitor<List<Question>>
{
    @Override
    public List<Question> visit(BinaryBooleanOperation binaryBooleanOperation) {
        List<Question> result = new ArrayList<>();
        List<Question> leftDependencies = binaryBooleanOperation.getLeftSide().accept(this);
        List<Question> rightDependencies = binaryBooleanOperation.getRightSide().accept(this);
        result.addAll(leftDependencies);
        result.addAll(rightDependencies);
        return result;
    }

    @Override
    public List<Question> visit(BooleanLiteral literal) {
        return new ArrayList<>();
    }

    @Override
    public List<Question> visit(Negation negation) {
        return negation.getOperand().accept(this);
    }

    @Override
    public List<Question> visit(BooleanQuestionReference booleanQuestionReference) {
        List<Question> result = new ArrayList<>();
        result.add(booleanQuestionReference.getQuestion());
        result.addAll(booleanQuestionReference.getQuestion().accept(this));
        return result;
    }

    @Override
    public List<Question> visit(NumericComparison numericComparison) {
        List<Question> result = new ArrayList<>();
        List<Question> leftDependencies = numericComparison.getLeftSide().accept(this);
        List<Question> rightDependencies = numericComparison.getRightSide().accept(this);
        result.addAll(leftDependencies);
        result.addAll(rightDependencies);
        return result;
    }

    @Override
    public List<Question> visit(BinaryNumericOperation binaryNumericOperation) {
        List<Question> result = new ArrayList<>();
        List<Question> leftDependencies = binaryNumericOperation.getLeftSide().accept(this);
        List<Question> rightDependencies = binaryNumericOperation.getRightSide().accept(this);
        result.addAll(leftDependencies);
        result.addAll(rightDependencies);
        return result;
    }

    @Override
    public List<Question> visit(NumericLiteral numericLiteral) {
        return new ArrayList<>();
    }

    @Override
    public List<Question> visit(NumericNegation numericNegation) {
        return numericNegation.getInnerExpression().accept(this);
    }

    @Override
    public List<Question> visit(NumericQuestionReference numericQuestionReference) {
        List<Question> result = new ArrayList<>();
        result.add(numericQuestionReference.getQuestion());
        result.addAll(numericQuestionReference.getQuestion().accept(this));
        return result;
    }

    @Override
    public List<Question> visit(BooleanQuestion booleanQuestion) {
        if (booleanQuestion.getValueExpression() != null) {
            return booleanQuestion.getValueExpression().accept(this);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Question> visit(DateQuestion dateQuestion) {
        return new ArrayList<>();
    }

    @Override
    public List<Question> visit(DecimalQuestion decimalQuestion) {
        if (decimalQuestion.getValueExpression() != null) {
            return decimalQuestion.getValueExpression().accept(this);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Question> visit(IntegerQuestion integerQuestion) {
        if (integerQuestion.getValueExpression() != null) {
            return integerQuestion.getValueExpression().accept(this);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Question> visit(MoneyQuestion moneyQuestion) {
        if (moneyQuestion.getValueExpression() != null) {
            return moneyQuestion.getValueExpression().accept(this);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Question> visit(StringQuestion stringQuestion) {
        return new ArrayList<>();
    }
}
