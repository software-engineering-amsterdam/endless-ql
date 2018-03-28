package QL.Analysis;

import QL.ParseObjectsQL.Expressions.BinaryExpression;
import QL.ParseObjectsQL.Expressions.BinaryExpressions.*;
import QL.ParseObjectsQL.Expressions.ConstantExpression;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.*;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NegationExpression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NotExpression;
import QL.ParseObjectsQL.Form;
import QL.ParseObjectsQL.Question;
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
        try {
            for (Question question : form.getQuestions()) {
                question.getAnswer().accept(this);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private EvaluationType checkArithmeticExpression(BinaryExpression expression, String operator){
        //TODO: clean up, improve exception message
        boolean validType = expression.getExprLeft().evaluate().isArithmetic() && expression.getExprRight().evaluate().isArithmetic();

        if(validType){ return expression.returnType();}
        else{ throw new IllegalArgumentException("Invalid '" + operator + "' expression: non-numeric argument passed at line: "  + expression.getLineNumber());}
    }

    private EvaluationType checkLogicalExpression(BinaryExpression expression, String operator){
        //TODO: clean up, improve exception message
        boolean validType = expression.getExprLeft().evaluate().isLogical() && expression.getExprRight().evaluate().isLogical();

        if(validType){ return EvaluationType.Boolean;}
        else{ throw new IllegalArgumentException("Invalid '" + operator + "' expression: non-boolean argument passed at line: " + expression.getLineNumber());}
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

        if(validType){ return expression.accept(this);}
        else { throw new IllegalArgumentException("Invalid '==' expression: incompatible types." + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(NotEqualExpression expression){
        boolean validType = expression.getExprLeft().accept(this) == expression.getExprRight().accept(this);

        if(validType){ return expression.accept(this);}
        else { throw new IllegalArgumentException("Invalid '!=' expression: incompatible types." + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(GreaterOrEqualExpression expression) {
        return checkLogicalExpression(expression, ">=");
    }

    @Override
    public EvaluationType visit(GreaterThanExpression expression) {
        return checkLogicalExpression(expression, ">");
    }

    @Override
    public EvaluationType visit(LessOrEqualExpression expression) {
        return checkLogicalExpression(expression, "<=");
    }

    @Override
    public EvaluationType visit(LessThanExpression expression) {
        return checkLogicalExpression(expression, "<");
    }

    @Override
    public EvaluationType visit(MultiplicationExpression expression) {
        return checkArithmeticExpression(expression, "*");
    }

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
        //TODO: change manner in which arithmetic check is done, shouldn't evaluate beforehand
        boolean validType = expression.evaluate().isArithmetic();

        if(validType) { return expression.accept(this);}
        else { throw new IllegalArgumentException("Invalid Negation ('-' <Number>) expression: non-numeric argument passed. at:" + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(NotExpression expression) {
        boolean validType = expression.accept(this) == EvaluationType.Boolean;

        if(validType) { return expression.accept(this);}
        else { throw new IllegalArgumentException("Invalid Not ('!' <Boolean>) expression: non-boolean argument passed. at:" + expression.getLineNumber());}
    }

    @Override
    public EvaluationType visit(ConstantExpression expression) {
        return this.expressionTable.getExpression(expression.getIdentifier()).returnType();
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
