package gui;

import classes.Block;
import classes.expressions.BooleanExpression;
import classes.expressions.BooleanLiteral;
import classes.expressions.Expression;
import classes.expressions.ExpressionVisitor;
import classes.form.Form;
import classes.statements.IfStatement;
import classes.statements.Question;
import classes.statements.Statement;
import classes.statements.StatementVisitor;

import java.util.HashMap;

/**
 * @author ajm
 */
public class FormGUIVisitor implements classes.form.FormVisitor, StatementVisitor, ExpressionVisitor<Boolean> {

    private FormGUIBuilder formBuilder;
    private HashMap<Question, BooleanExpression> statementConditionsMap;


    public FormGUIVisitor(FormGUIBuilder formBuilder) {
        this.formBuilder = formBuilder;
        this.statementConditionsMap = new HashMap<>();
    }

    @Override
    public void visitForm(Form form) {
        form.getBlock().accept(this);
    }

    @Override
    public void visitBlock(Block block) {
        for(Statement statement: block.getStatements()) {
            statement.accept(this);
        }

    }

    @Override
    public void visitQuestion(Question question) {
        statementConditionsMap.put(question, null);
    }

    @Override
    public void visitQuestionWithExpr(Question question, BooleanExpression expression) {
        if(expression != null) {
            statementConditionsMap.put(question, expression);
        } else {
            statementConditionsMap.put(question, new BooleanLiteral(true));
        }
    }

    @Override
    public void visitIfStatementWithExpr(IfStatement ifStatement, BooleanExpression expression) {
        Block block = ifStatement.getBlock();
        for (Statement statement : block.getStatements()) {
            if(expression != null) {
                //TODO: add handling for another condition
                statement.accept(this, ifStatement.getExpression());
            } else {
                statement.accept(this, ifStatement.getExpression());
            }
        }
    }

    public HashMap<Question, BooleanExpression> getStatementConditionsMap() {
        return statementConditionsMap;
    }

    public Boolean validateExpression(BooleanExpression expression) {
        return expression.accept(this);
    }

    @Override
    public Boolean visit(BooleanLiteral node) {
        return null;
    }
}
