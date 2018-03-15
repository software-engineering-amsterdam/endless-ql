package parsing;

import classes.Block;
import classes.CodeBlock;
import classes.Configuration;
import classes.TreeNode;
import classes.expressions.BooleanExpression;
import classes.expressions.BooleanLiteral;
import classes.expressions.Expression;
import classes.form.Form;
import classes.statements.IfStatement;
import classes.statements.Question;
import classes.statements.Statement;
import jdk.nashorn.api.tree.Tree;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AST_Visitor extends QLBaseVisitor<Object> {
    Configuration config = new Configuration();
    private Map<String, Question> memory = new HashMap<String, Question>();
    private Map<String, Object> valueMap = new HashMap<String, Object>();

    @Override
    public Object visitForm(QLParser.FormContext ctx) {
       Block block = (Block) ctx.block().accept(this);
       Form form = new Form(ctx.IDENTIFIER().getText(), block, CodeBlock.getCodeBlock(ctx));
       return form;
    }

    @Override
    public Object visitBlock(QLParser.BlockContext ctx) {
        CodeBlock code = CodeBlock.getCodeBlock(ctx);
        List<Statement> statements = new ArrayList<Statement>();
        Block block = new Block(code, statements,true);
        List<QLParser.IfStatementContext> ifStatementContextList = ctx.ifStatement();
        List<QLParser.QuestionContext> questionContextList = ctx.question();

        for(QLParser.IfStatementContext ifStatementContext : ifStatementContextList) {
            block.getStatements().add((Statement) ifStatementContext.accept(this));
        }
        for(QLParser.QuestionContext questionContext : questionContextList) {
            block.getStatements().add((Statement) questionContext.accept(this));
        }

        return block;
    }

    @Override
    public Object visitQuestion(QLParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        String questionString = ctx.STR().getText();
        return new Question(codeBlock, questionString, visit(ctx.type()), id);
//        memory.put(id, question);
//        return question;
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
//        String id = ctx.getText();
//        Question question = getQuestion(id);
//        return castToType(question.getType(), Boolean.class);
        CodeBlock code = CodeBlock.getCodeBlock(ctx);
        BooleanExpression expression = new BooleanExpression(code);
        return expression;

    }


    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        CodeBlock codeBlock = CodeBlock.getCodeBlock(ctx);
        BooleanExpression expression = (BooleanExpression) ctx.booleanExpression().accept(this);
        Block block = (Block) ctx.block(0).accept(this);
        return new IfStatement(expression, codeBlock, block);
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

    // visitor methods for types
    @Override
    public Boolean visitBooltype(QLParser.BooltypeContext ctx) {
        return new Boolean(false);
    }

    @Override
    public String visitStringtype(QLParser.StringtypeContext ctx) {
        return new String("");
    }

    @Override
    public Integer visitIntegertype(QLParser.IntegertypeContext ctx) {
        return new Integer(0);
    }

    @Override
    public Double visitMoneytype(QLParser.MoneytypeContext ctx) {
        return new Double(0.0);
    }

    @Override
    public Date visitDatetype(QLParser.DatetypeContext ctx) {
        return new Date();
    }

    @Override
    public Double visitDecimaltype(QLParser.DecimaltypeContext ctx) {
        return new Double("0.0");
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
    private Question getQuestion(String id){
        Object result = memory.get(id);

        if(memory.get(id) == null){
            (new Error("Variable " + id + " does not exist")).printStackTrace();
            System.exit(1);
        }

        return memory.get(id);
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

    public void update(String key, Object value) {
        valueMap.put(key, value);
    }

    public Map<String, Object> getValueMap() {
        return this.valueMap;
    }

    public Object checkExpression() {
        return new Object();
    }

    public Boolean validateExpression() {
        return true;
    }
}