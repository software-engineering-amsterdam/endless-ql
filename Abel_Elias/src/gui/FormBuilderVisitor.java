package gui;

import classes.Block;
import classes.expressions.BooleanLiteral;
import classes.expressions.Expression;
import classes.form.Form;
import classes.form.FormVisitor;
import classes.statements.IfStatement;
import classes.statements.Question;
import classes.statements.Statement;
import classes.statements.StatementVisitor;

import java.util.HashMap;

public class FormBuilderVisitor implements FormVisitor, StatementVisitor {

    private FormBuilder formBuilder;
    private HashMap<Question, Expression> statementConditionsMap;


    public FormBuilderVisitor(FormBuilder formBuilder) {
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
    public void visitQuestionWithExpr(Question question, Expression expression) {
        if(expression != null) {
            statementConditionsMap.put(question, expression);
        } else {
            statementConditionsMap.put(question, new BooleanLiteral(true));
        }
    }

    @Override
    public void visitIfStatementWithExpr(IfStatement ifStatement, Expression expression) {
        Block block = ifStatement.getBlock();
        for (Statement statement : block.getStatements()) {
             statement.accept(this, ifStatement.getExpression());
        }
    }

    public HashMap<Question, Expression> getStatementConditionsMap() {
        return statementConditionsMap;
    }
}
