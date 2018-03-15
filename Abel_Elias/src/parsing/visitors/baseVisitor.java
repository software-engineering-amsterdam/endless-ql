package parsing.visitors;

import classes.*;

import parsing.checkers.TypeChecker;
import parsing.checkers.VariableChecker;
import parsing.gen.*;

import java.util.Date;
import java.util.HashMap;

public class BaseVisitor extends QLBaseVisitor {
    HashMap<String, Question> questionMap;

    public BaseVisitor(){
        super();
        this.questionMap = new HashMap<>();
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
        new VariableChecker(questionMap, ctx.block());
        new TypeChecker(questionMap, ctx.block());

        visitChildren(ctx);
        return questionMap;
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        String questionString = ctx.STR().getText();
        Object initialValue = visit(ctx.type());

        Question question = new Question(codeBlock, questionString, initialValue, false);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        String questionString = ctx.STR().getText();
        Question question = new Question(codeBlock, questionString, visit(ctx.expression()), true);
        questionMap.put(id, question);

        return questionMap;
    }

    // visitor methods for types
    @Override
    public Boolean visitBooltype(QLParser.BooltypeContext ctx) {
        return false;
    }

    @Override
    public String visitStringtype(QLParser.StringtypeContext ctx) {
        return "";
    }

    @Override
    public Integer visitIntegertype(QLParser.IntegertypeContext ctx) {
        return 0;
    }

    @Override
    public Double visitMoneytype(QLParser.MoneytypeContext ctx) {
        return 0.0;
    }

    @Override
    public Date visitDatetype(QLParser.DatetypeContext ctx) {
        return new Date();
    }

    @Override
    public Double visitDecimaltype(QLParser.DecimaltypeContext ctx) {
        return 0.0;
    }

    // visit boolean values
    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();
        Question question = getQuestion(id);
        return castToType(question.getValue(), Boolean.class);
    }

    @Override
    public Object visitBoolBraces(QLParser.BoolBracesContext ctx) {
        return visit(ctx.booleanExpression());
    }

    @Override
    public Object visitBoolOperation(QLParser.BoolOperationContext ctx) {
        return super.visitBoolOperation(ctx);
    }

    @Override
    public Object visitBoolOperator(QLParser.BoolOperatorContext ctx) {
        return super.visitBoolOperator(ctx);
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        QLParser.BooleanExpressionContext boolExprCtx = ctx.booleanExpression();
        QLParser.BlockContext blockCtx = ctx.block();

        visit(boolExprCtx);
        visit(blockCtx);

        return questionMap;
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
    public Integer visitIntValue(QLParser.IntValueContext ctx) {
        return Integer.parseInt(ctx.getText());
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
    public Number visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
       Question q = questionMap.get(ctx.IDENTIFIER().getText());
       return (Number) q.getValue();
    }

    @Override
    public Object visitNumBraces(QLParser.NumBracesContext ctx) {
        return visit(ctx.numberExpression());
    }

    @Override
    public Number visitNumOperation(QLParser.NumOperationContext ctx) {
        Double left = (Double) visit(ctx.left);
        String operator = ctx.numberOperator().getText();
        Double right = (Double) visit(ctx.right);

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

    public void addQuestions(HashMap<String, Question> questionMap){
        this.questionMap.putAll(questionMap);
    }
}