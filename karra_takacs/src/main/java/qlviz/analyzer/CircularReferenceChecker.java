package qlviz.analyzer;

import qlviz.interpreter.TypedQuestionCollector;
import qlviz.interpreter.TypedQuestionWalker;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.expressions.Expression;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.booleanExpressions.*;
import qlviz.model.expressions.numericExpressions.*;
import qlviz.model.question.*;

import java.util.*;

public class CircularReferenceChecker implements
        ExpressionVisitor,
        VoidQuestionVisitor,
        Analyzer
{

    private NumericExpressionReferenceChecker numericExpressionVisitor;
    private BooleanExpressionReferenceChecker booleanExpressionVisitor;
    private HashSet<Question> visited;
    private Queue<Question> processingQueue;
    private Form form;

   public boolean hasCircularReference(Expression expression) {
        this.visited = new HashSet<>();
        this.processingQueue = new LinkedList<>();
        this.numericExpressionVisitor = new NumericExpressionReferenceChecker(this.processingQueue);
        this.booleanExpressionVisitor = new BooleanExpressionReferenceChecker(this.numericExpressionVisitor, this.processingQueue);
        expression.accept(this);
        while (this.processingQueue.size() > 0) {
            Question nextElement = this.processingQueue.remove();
            if (visited.contains(nextElement)) {
                return true;
            }
            nextElement.accept(this);
            visited.add(nextElement);
        }
        return false;
    }

    @Override
    public void visit(BooleanQuestion booleanQuestion) {
        if (booleanQuestion.getValueExpression() != null) {
            booleanQuestion.getValueExpression().accept(this);
        }
    }

    @Override
    public void visit(DateQuestion dateQuestion) {
        return;
    }

    @Override
    public void visit(DecimalQuestion decimalQuestion) {
        if (decimalQuestion.getValueExpression() != null) {
            decimalQuestion.getValueExpression().accept(this);
        }
    }

    @Override
    public void visit(IntegerQuestion integerQuestion) {
        if (integerQuestion.getValueExpression() != null) {
            integerQuestion.getValueExpression().accept(this);
        }
    }

    @Override
    public void visit(MoneyQuestion moneyQuestion) {
        if (moneyQuestion.getValueExpression() != null) {
            moneyQuestion.getValueExpression().accept(this);
        }
    }

    @Override
    public void visit(StringQuestion stringQuestion) {
        return;
    }

    @Override
    public void initialize(Form form) {
        this.form = form;
    }

    private List<AnalysisResult> analyzeConditionalBlock(ConditionalBlock conditionalBlock) {
        List<AnalysisResult> analysisResults = new ArrayList<>();

        if (this.hasCircularReference(conditionalBlock.getCondition())) {
            analysisResults.add(new CircularReferenceResult(conditionalBlock));
        }

        for (QuestionBlock innerBlock : conditionalBlock.getQuestionBlocks()) {
            innerBlock.getBlocks()
                    .stream()
                    .map(this::analyzeConditionalBlock)
                    .forEach(analysisResults::addAll);
        }

        return analysisResults;
    }

    @Override
    public List<AnalysisResult> analyze() {
        List<AnalysisResult> analysisResults = new ArrayList<>();

        TypedQuestionCollector typedQuestionCollector = new TypedQuestionWalker();
        for (BooleanQuestion question : typedQuestionCollector.collectBooleanQuestions(this.form)) {
            if (question.getValueExpression() != null) {
                if (this.hasCircularReference(question.getValueExpression())) {
                   analysisResults.add(new CircularReferenceResult(question));
                }
            }
        }
       for (NumericQuestion question : typedQuestionCollector.collectNumericQuestions(this.form)) {
            if (question.getValueExpression() != null) {
                if (this.hasCircularReference(question.getValueExpression())) {
                   analysisResults.add(new CircularReferenceResult(question));
                }
            }
        }

        return analysisResults;
    }

    public void visit(BinaryNumericOperation binaryNumericOperation) {
        numericExpressionVisitor.visit(binaryNumericOperation);
    }

    public void visit(NumericLiteral numericLiteral) {
        numericExpressionVisitor.visit(numericLiteral);
    }

    public void visit(NumericNegation numericNegation) {
        numericExpressionVisitor.visit(numericNegation);
    }

    public void visit(NumericQuestionReference numericQuestionReference) {
        numericExpressionVisitor.visit(numericQuestionReference);
    }

    public void visit(BinaryBooleanOperation binaryBooleanOperation) {
        booleanExpressionVisitor.visit(binaryBooleanOperation);
    }

    public void visit(BooleanLiteral literal) {
        booleanExpressionVisitor.visit(literal);
    }

    public void visit(Negation negation) {
        booleanExpressionVisitor.visit(negation);
    }

    public void visit(BooleanQuestionReference booleanQuestionReference) {
        booleanExpressionVisitor.visit(booleanQuestionReference);
    }

    public void visit(NumericComparison numericComparison) {
        booleanExpressionVisitor.visit(numericComparison);
    }

    @Override
    public void visit(NumericExpression numericExpression) {
        numericExpression.accept(this.numericExpressionVisitor);
    }

    @Override
    public void visit(BooleanExpression booleanExpression) {
        booleanExpression.accept(this.booleanExpressionVisitor);
    }

    private class NumericExpressionReferenceChecker implements NumericExpressionVisitor {
        private final Queue<Question> processingQueue;

        private NumericExpressionReferenceChecker(Queue<Question> processingQueue) {
            this.processingQueue = processingQueue;
        }

        @Override
        public void visit(BinaryNumericOperation binaryNumericOperation) {
            binaryNumericOperation.getLeftSide().accept(this);
            binaryNumericOperation.getRightSide().accept(this);
        }

        @Override
        public void visit(NumericLiteral numericLiteral) {
            return;
        }

        @Override
        public void visit(NumericNegation numericNegation) {
            numericNegation.getInnerExpression().accept(this);
        }

        @Override
        public void visit(NumericQuestionReference numericQuestionReference) {
            this.processingQueue.add(numericQuestionReference.getQuestion());
        }
    }

    private class BooleanExpressionReferenceChecker implements BooleanExpressionVisitor {

        private final NumericExpressionReferenceChecker numericExpressionReferenceChecker;
        private Queue<Question> processingQueue;

        public BooleanExpressionReferenceChecker(
                NumericExpressionReferenceChecker numericExpressionReferenceChecker, Queue<Question> processingQueue) {
            this.numericExpressionReferenceChecker = numericExpressionReferenceChecker;
            this.processingQueue = processingQueue;
        }

        @Override
        public void visit(BinaryBooleanOperation binaryBooleanOperation) {
            binaryBooleanOperation.getLeftSide().accept(this);
            binaryBooleanOperation.getRightSide().accept(this);
        }

        @Override
        public void visit(BooleanLiteral literal) {
            return;
        }

        @Override
        public void visit(Negation negation) {
            negation.getOperand().accept(this);
        }

        @Override
        public void visit(BooleanQuestionReference booleanQuestionReference) {
           this.processingQueue.add(booleanQuestionReference.getQuestion());
        }

        @Override
        public void visit(NumericComparison numericComparison) {
            numericComparison.getLeftSide().accept(numericExpressionReferenceChecker);
            numericComparison.getRightSide().accept(numericExpressionReferenceChecker);
        }
    }
}
