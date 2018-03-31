package qlviz.analyzer;

import qlviz.interpreter.TypedQuestionCollector;
import qlviz.model.Form;
import qlviz.model.expressions.BooleanExpressionGetter;
import qlviz.model.expressions.NumericExpressionGetter;
import qlviz.model.question.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTypeChecker implements QuestionVisitor<AnalysisResult>, Analyzer
{

    private final TypedQuestionCollector typedQuestionCollector;
    private final BooleanExpressionGetter booleanExpressionGetter = new BooleanExpressionGetter();
    private final NumericExpressionGetter numericExpressionGetter = new NumericExpressionGetter();
    private Form form;

    public ExpressionTypeChecker(TypedQuestionCollector typedQuestionCollector) {

        this.typedQuestionCollector = typedQuestionCollector;
    }

    @Override
    public void initialize(Form form) {
        this.form = form;
    }

    @Override
    public List<AnalysisResult> analyze() {
        var questions = new ArrayList<Question>();
        questions.addAll(this.typedQuestionCollector.collectBooleanQuestions(this.form));
        questions.addAll(this.typedQuestionCollector.collectNumericQuestions(this.form));

        var results = new ArrayList<AnalysisResult>();
        for (var question : questions) {
            var result = question.accept(this);
            if (result != null) {
                results.add(result);
            }
        }

        return results;
    }


    @Override
    public AnalysisResult visit(BooleanQuestion booleanQuestion) {
        if (booleanQuestion.getValueExpression() != null) {
            var expression = booleanQuestion.getValueExpression().accept(booleanExpressionGetter);
            if (expression == null) {
                return new MismatchedTypeResult(booleanQuestion.getQuestionContext());
            }
        }
        return null;
    }

    @Override
    public AnalysisResult visit(DateQuestion dateQuestion) {
        return null;
    }

    @Override
    public AnalysisResult visit(DecimalQuestion decimalQuestion) {
        if (decimalQuestion.getValueExpression() != null) {
           var expression = decimalQuestion.getValueExpression().accept(numericExpressionGetter);
           if (expression == null) {
               return new MismatchedTypeResult(decimalQuestion.getQuestionContext());
           }
        }
        return null;
    }

    @Override
    public AnalysisResult visit(IntegerQuestion integerQuestion) {
        if (integerQuestion.getValueExpression() != null) {
           var expression = integerQuestion.getValueExpression().accept(numericExpressionGetter);
           if (expression == null) {
               return new MismatchedTypeResult(integerQuestion.getQuestionContext());
           }
        }
        return null;
    }

    @Override
    public AnalysisResult visit(MoneyQuestion moneyQuestion) {
        if (moneyQuestion.getValueExpression() != null) {
           var expression = moneyQuestion.getValueExpression().accept(numericExpressionGetter);
           if (expression == null) {
               return new MismatchedTypeResult(moneyQuestion.getQuestionContext());
           }
        }
        return null;
    }

    @Override
    public AnalysisResult visit(StringQuestion stringQuestion) {
        return null;
    }
}
