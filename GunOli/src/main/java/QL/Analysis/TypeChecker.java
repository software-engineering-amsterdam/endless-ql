package QL.Analysis;

import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.BinaryExpressions.AdditionExpression;
import QL.AST.Expressions.BinaryExpressions.AndExpression;
import QL.AST.Expressions.BinaryExpressions.DivisionExpression;
import QL.AST.Expressions.BinaryExpressions.EqualExpression;
import QL.AST.Expressions.BinaryExpressions.GreaterOrEqualExpression;
import QL.AST.Expressions.BinaryExpressions.GreaterThanExpression;
import QL.AST.Expressions.BinaryExpressions.LessOrEqualExpression;
import QL.AST.Expressions.BinaryExpressions.LessThanExpression;
import QL.AST.Expressions.BinaryExpressions.MultiplicationExpression;
import QL.AST.Expressions.BinaryExpressions.NotEqualExpression;
import QL.AST.Expressions.BinaryExpressions.OrExpression;
import QL.AST.Expressions.BinaryExpressions.SubtractExpression;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import QL.AST.Expressions.ExpressionConstants.DateConstant;
import QL.AST.Expressions.ExpressionConstants.DecimalConstant;
import QL.AST.Expressions.ExpressionConstants.IntegerConstant;
import QL.AST.Expressions.ExpressionConstants.MoneyConstant;
import QL.AST.Expressions.ExpressionConstants.StringConstant;
import QL.AST.Expressions.ExpressionConstants.UndefinedConstant;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Expressions.UnaryExpressions.NegationExpression;
import QL.AST.Expressions.UnaryExpressions.NotExpression;
import QL.AST.Form;
import QL.AST.Question;
import QL.QLVisitor.ExpressionTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypeChecker implements ExpressionVisitorInterface<EvaluationType>, QuestionVisitorInterface<EvaluationType> {

    private final Form form;
    private final ExpressionTable expressionTable;

    public TypeChecker(Form form, ExpressionTable expressionTable){
        this.form = form;
        this.expressionTable = expressionTable;
    }

    public void typeCheck(){
        Map<String, Question> checkedQuestionMap = new HashMap<>();

        for (Question question : form.getQuestions()) {
            question.getCondition().accept(this);   // check conditions to match boolean
            question.accept(this); // checks type assignments

            checkRepeatAssignment(question, checkedQuestionMap);

            checkedQuestionMap.put(question.getIdentifier(), question);
        }
    }

    public void checkRepeatAssignment(Question question, Map<String, Question> questionIdentifierMap){
        if(questionIdentifierMap.containsKey(question.getIdentifier())){
            throw new IllegalArgumentException("Invalid Assignment: identifier '" + question.getIdentifier()
                + "' at ql:" + question.getLineNumber() + " already assigned at ql:"
                + questionIdentifierMap.get(question.getIdentifier()).getLineNumber());
        }
    }

    public void detectCyclicDependencies(){
        CyclicDependencyDetector cyclicDependencyDetector = new CyclicDependencyDetector(form);
        cyclicDependencyDetector.detectCycles();
    }

    public void detectLabelDuplication(){
        LabelDuplicationDetector duplicationDetector = new LabelDuplicationDetector(form);
        Map<String, Set<Question>> labelMap = duplicationDetector.detectDuplicateLabels();

        ArrayList<String> warnings = issueWarnings(labelMap);

        if(!warnings.isEmpty()){
            for(String message : warnings) {
                System.out.println(message);
            }
        }
    }

    private ArrayList<String> issueWarnings(Map<String, Set<Question>> labelMap){
        ArrayList<String> warnings = new ArrayList<>();

        for(Map.Entry<String, Set<Question>> entry : labelMap.entrySet()){
            if(entry.getValue().size() > 1){
                String referencedIdentifiers = "";
                for(Question question : entry.getValue()){
                    referencedIdentifiers += "\t" + question.getIdentifier() + "\n";
                }
                warnings.add("Label: " + entry.getKey() + " mapped to multiple questions:\n" + referencedIdentifiers);
            }
        }
        return warnings;
    }

    private EvaluationType checkArithmeticExpression(BinaryExpression expression, String operator){
        EvaluationType leftType = expression.getExprLeft().accept(this);
        EvaluationType rightType = expression.getExprRight().accept(this);
        boolean validType = leftType.isArithmetic() && rightType.isArithmetic();

        if(validType){ return leftType.hasPriority(rightType);}

        throw new IllegalArgumentException("Invalid arithmetic expression: non-numeric argument passed at ql: "
                                            + expression.getLineNumber());
    }

    private EvaluationType checkComparisonExpression(BinaryExpression expression, String operator){
        EvaluationType leftType = expression.getExprLeft().accept(this);
        EvaluationType rightType = expression.getExprRight().accept(this);
        boolean validType = leftType.isArithmetic() && rightType.isArithmetic();

        if(validType){ return EvaluationType.Boolean;}

        throw new IllegalArgumentException("Invalid logical expression: non-boolean argument passed at ql: "
                                            + expression.getLineNumber());
    }

    private EvaluationType checkLogicalExpression(BinaryExpression expression, String operator){
        EvaluationType leftType = expression.getExprLeft().accept(this);
        EvaluationType rightType = expression.getExprRight().accept(this);
        boolean validType = leftType.isLogical() && rightType.isLogical();

        if(validType){ return EvaluationType.Boolean;}

        throw new IllegalArgumentException("Invalid logical expression: non-boolean argument passed at ql: "
                                            + expression.getLineNumber());
    }

    @Override
    public EvaluationType visit(Question question) {
        if(question.isPredefined()){
            EvaluationType type = question.getAnswer().accept(this);

            if(type != question.getType()){
                throw new IllegalArgumentException("Invalid assignment: cannot assign " + type + " to question type " +
                                                    question.getType() +" at ql: " + question.getLineNumber());
            }
        }

        return question.getType();
    }

    @Override
    public EvaluationType visit(Expression expression) { return expression.accept(this); }

    @Override
    public EvaluationType visit(AdditionExpression expression) {
        return checkArithmeticExpression(expression, "+");
    }

    @Override
    public EvaluationType visit(AndExpression expression) {
        return checkLogicalExpression(expression, "&&");
    }

    @Override
    public EvaluationType visit(DivisionExpression expression) {
        return checkArithmeticExpression(expression, "/");
    }

    @Override
    public EvaluationType visit(EqualExpression expression) {
        boolean validType = expression.getExprLeft().accept(this) == expression.getExprRight().accept(this);

        if(validType){ return expression.getExprLeft().accept(this);}

        throw new IllegalArgumentException("Invalid '==' expression: incompatible types. at ql:" + expression.getLineNumber());
    }

    @Override
    public EvaluationType visit(NotEqualExpression expression){
        boolean validType = expression.getExprLeft().accept(this) == expression.getExprRight().accept(this);

        if(validType){ return expression.getExprLeft().accept(this);}

        throw new IllegalArgumentException("Invalid '!=' expression: incompatible types. at ql:" + expression.getLineNumber());
    }

    @Override
    public EvaluationType visit(GreaterOrEqualExpression expression) {
        return checkComparisonExpression(expression, ">=");
    }

    @Override
    public EvaluationType visit(GreaterThanExpression expression) {
        return checkComparisonExpression(expression, ">");
    }

    @Override
    public EvaluationType visit(LessOrEqualExpression expression) { return checkComparisonExpression(expression, "<="); }

    @Override
    public EvaluationType visit(LessThanExpression expression) {
        return checkComparisonExpression(expression, "<");
    }

    @Override
    public EvaluationType visit(MultiplicationExpression expression) { return checkArithmeticExpression(expression, "*"); }

    @Override
    public EvaluationType visit(OrExpression expression) {
        return checkLogicalExpression(expression, "||");
    }

    @Override
    public EvaluationType visit(SubtractExpression expression) {
        return checkArithmeticExpression(expression, "-");
    }

    @Override
    public EvaluationType visit(NegationExpression expression) {
        boolean validType = expression.getExpression().accept(this).isArithmetic();

        if(validType) { return expression.getExpression().accept(this);}

        throw new IllegalArgumentException("Invalid Negation ('-' <Number>) expression: non-numeric argument passed. at ql:"
                                            + expression.getLineNumber());
    }

    @Override
    public EvaluationType visit(NotExpression expression) {
        boolean validType = expression.getExpression().accept(this).isLogical();

        if(validType) { return expression.getExpression().accept(this);}

        throw new IllegalArgumentException("Invalid Not ('!' <Boolean>) expression: non-boolean argument passed. at ql:"
                                            + expression.getLineNumber());
    }

    @Override
    public EvaluationType visit(IdentifierExpression expression) {
        if(this.expressionTable.exisitsIn(expression.getIdentifier())){
            return this.expressionTable.getExpression(expression.getIdentifier()).accept(this);
        }
        throw new IllegalArgumentException("Reference to undefined question '" + expression.getIdentifier()
                                            + "' at ql:" + expression.getLineNumber());
    }

    @Override
    public EvaluationType visit(BooleanConstant expression) {
        return EvaluationType.Boolean;
    }

    @Override
    public EvaluationType visit(DateConstant expression) {
        return EvaluationType.Date;
    }

    @Override
    public EvaluationType visit(DecimalConstant expression) {
        return EvaluationType.Decimal;
    }

    @Override
    public EvaluationType visit(IntegerConstant expression) {
        return EvaluationType.Integer;
    }

    @Override
    public EvaluationType visit(MoneyConstant expression) {
        return EvaluationType.Money;
    }

    @Override
    public EvaluationType visit(StringConstant expression) {
        return EvaluationType.String;
    }

    @Override
    public EvaluationType visit(UndefinedConstant expression) {
        return EvaluationType.Undefined;
    }
}
