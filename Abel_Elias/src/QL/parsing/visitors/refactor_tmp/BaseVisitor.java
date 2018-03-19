package QL.parsing.visitors.refactor_tmp;

import QL.classes.*;

import QL.classes.values.*;
import QL.parsing.gen.*;

import java.util.HashMap;

public class BaseVisitor extends QLBaseVisitor {
    HashMap<String, Question> questionMap;

    public BaseVisitor(){
        this.questionMap = new HashMap<>();
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        return questionMap.get(id);
    }

    public BaseVisitor(QLParser.FormContext tree){
        this();
        visit(tree);
    }

    public HashMap<String, Question> getQuestions(){
        return questionMap;
    }

    // Node visitor
    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        visitChildren(ctx);
        return questionMap;
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        String questionString = ctx.STR().getText();
        Value value = (Value) visit(ctx.type());

        Question question = new Question(questionString, value, false);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        String questionString = ctx.STR().getText();
        Value value =  (Value) visit(ctx.type());
        value.setValueGeneric(visit(ctx.expression()));

        Question question = new Question(questionString, value, true);
        questionMap.put(id, question);

        return questionMap;
    }

    // visitor methods for types
    @Override
    public BooleanValue visitBooltype(QLParser.BooltypeContext ctx) {
        return new BooleanValue();
    }

    @Override
    public Boolean visitCompOperation(QLParser.CompOperationContext ctx) {
        Double left = (Double) visit(ctx.left);
        String operator = ctx.comparisonOperator().getText();
        Double right = (Double) visit(ctx.right);

        switch (operator) {
            case "<":
                return left < right;
            case ">":
                return left > right;
            case "!=":
                return left != right;
            case "==":
                return left == right;
        }

        return null;
    }

    // VISIT TYPES
    @Override
    public StringValue visitStringtype(QLParser.StringtypeContext ctx) {
        return new StringValue();
    }

    @Override
    public IntegerValue visitIntegertype(QLParser.IntegertypeContext ctx) {
        return new IntegerValue();
    }

    @Override
    public MoneyValue visitMoneytype(QLParser.MoneytypeContext ctx) {
        return new MoneyValue();
    }

    @Override
    public DateValue visitDatetype(QLParser.DatetypeContext ctx) {
        return new DateValue();
    }

    @Override
    public DecimalValue visitDecimaltype(QLParser.DecimaltypeContext ctx) {
        return new DecimalValue();
    }

    // Visitor methods for values
    @Override
    public String visitString(QLParser.StringContext ctx) {
        return ctx.STR().getText();
    }

    @Override
    public Boolean visitBoolValue(QLParser.BoolValueContext ctx) {
        return Boolean.parseBoolean(ctx.getText());
    }

    @Override
    public Double visitIntValue(QLParser.IntValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitMoneyValue(QLParser.MoneyValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitDecValue(QLParser.DecValueContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    // Arithmetic functions
    @Override
    public Double visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
       Question q = questionMap.get(ctx.IDENTIFIER().getText());
       return ((NumericValue) q.getValue()).getComputationValue();
    }

    @Override
    public Double visitNumBraces(QLParser.NumBracesContext ctx) {
        return (double) visit(ctx.numberExpression());
    }

    @Override
    public Number visitNumOperation(QLParser.NumOperationContext ctx) {
        Double left = (double) visit(ctx.left);
        String operator = ctx.numberOperator().getText();
        Double right = (double) visit(ctx.right);

        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
        }

        return null;
    }

    // getters & setters
    public Question getQuestion(String id){
        Object result = questionMap.get(id);

        if(questionMap.get(id) == null){
            (new Error("Variable " + id + " does not exist")).printStackTrace();
            System.exit(1);
        }

        return questionMap.get(id);
    }

    private <T extends Object> T castToType(Object value, Class<T> type) {
        try{
            return type.cast(value);
        }catch(ClassCastException e){
            //TODO: print error about
            e.printStackTrace();
            System.exit(1);
        }

        return type.cast(value);
    }

    public void setQuestionMap(HashMap<String, Question> questionMap){
        this.questionMap = questionMap;
    }

    public void addQuestion(String id, Question q){
        this.questionMap.put(id,q);
    }

    public boolean containsQuestion(String id){
        return questionMap.containsKey(id);
    }

    public void initQuestionMap(){
        this.questionMap = new HashMap<>();
    }

    public void addQuestions(HashMap<String, Question> questionMap){
        this.questionMap.putAll(questionMap);
    }

    public Boolean validateExpression(Question question) {
        return true;
    }

    public HashMap<String,Question> updateQuestions(HashMap<String, Question> questionHashMap) {
        return new HashMap<String, Question>();
    }
}