package parsing.visitors;

import classes.*;

import parsing.checkers.TypeChecker;
import parsing.checkers.VariableChecker;
import parsing.gen.*;

import java.util.HashMap;

public class baseVisitor extends QLBaseVisitor {
    HashMap<String, Question> questionMap;

    public baseVisitor(){
        super();
        this.questionMap = new HashMap<>();
    }

    public baseVisitor(QLParser.FormContext tree){
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
        Question question = new Question(codeBlock, questionString, visit(ctx.type()), true);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        String questionString = ctx.STR().getText();
        Question question = new Question(codeBlock, questionString, visit(ctx.type()), true);
        //questionMap.put(id, question.setValue(visit(ctx.expression())));
        questionMap.put(id, question);

        return questionMap;
    }

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

    // helper functions
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