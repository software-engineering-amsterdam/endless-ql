package QL.Analysis;

import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.BinaryExpressions.*;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.*;
import QL.AST.Expressions.UnaryExpressions.NegationExpression;
import QL.AST.Expressions.UnaryExpressions.NotExpression;
import QL.AST.Form;
import QL.AST.Question;
import QL.QLVisitor.ExpressionTable;
import java.util.*;

public class TypeChecker implements ExpressionVisitorInterface<EvaluationType> {

    private final Form form;
    private final ExpressionTable expressionTable;

    public TypeChecker(Form form, ExpressionTable expressionTable){
        this.form = form;
        this.expressionTable = expressionTable;
    }

    public void typeCheck(){
        for (Question question : form.getQuestions()) {
            question.getCondition().accept(this);   //check conditions to match boolean
            question.getAnswer().accept(this);      //check answers to match question type
        }
    }

    private EvaluationType checkArithmeticExpression(BinaryExpression expression, String operator){
        EvaluationType leftType = expression.getExprLeft().accept(this);
        EvaluationType rightType = expression.getExprRight().accept(this);
        boolean validType = leftType.isArithmetic() && rightType.isArithmetic();

        if(validType){ return leftType.hasPriority(rightType);}
        else{ throw new IllegalArgumentException("Invalid arithmetic expression: non-numeric argument passed at line: "  + expression.getLineNumber());}
    }

    private EvaluationType checkComparisonExpression(BinaryExpression expression, String operator){
        EvaluationType leftType = expression.getExprLeft().accept(this);
        EvaluationType rightType = expression.getExprRight().accept(this);
        boolean validType = leftType.isArithmetic() && rightType.isArithmetic();

        if(validType){ return EvaluationType.Boolean;}
        else{ throw new IllegalArgumentException("Invalid logical expression: non-boolean argument passed at line: " + expression.getLineNumber());}
    }

    private EvaluationType checkLogicalExpression(BinaryExpression expression, String operator){
        EvaluationType leftType = expression.getExprLeft().accept(this);
        EvaluationType rightType = expression.getExprRight().accept(this);
        boolean validType = leftType.isLogical() && rightType.isLogical();

        if(validType){ return EvaluationType.Boolean;}
        else{ throw new IllegalArgumentException("Invalid logical expression: non-boolean argument passed at line: " + expression.getLineNumber());}
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
        else { throw new IllegalArgumentException("Invalid '==' expression: incompatible types." + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(NotEqualExpression expression){
        boolean validType = expression.getExprLeft().accept(this) == expression.getExprRight().accept(this);

        if(validType){ return expression.getExprLeft().accept(this);}
        else { throw new IllegalArgumentException("Invalid '!=' expression: incompatible types." + expression.getLineNumber());}
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
        else { throw new IllegalArgumentException("Invalid Negation ('-' <Number>) expression: non-numeric argument passed. at:" + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(NotExpression expression) {
        boolean validType = expression.getExpression().accept(this).isLogical();

        if(validType) { return expression.getExpression().accept(this);}
        else { throw new IllegalArgumentException("Invalid Not ('!' <Boolean>) expression: non-boolean argument passed. at:" + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(IdentifierExpression expression) {
        return this.expressionTable.getExpression(expression.getIdentifier()).accept(this);
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
}
