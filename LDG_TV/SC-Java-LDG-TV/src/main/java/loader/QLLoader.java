package loader;

import antlr.ql.FormBaseListener;
import antlr.ql.FormParser;
import domain.FormNode;
import domain.model.Condition;
import domain.model.IfASTNode;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.BooleanExpressionValue;
import domain.model.variable.*;
import domain.model.QuestionASTNode;

import java.util.ArrayList;
import java.util.List;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;

    private QLChecker qlChecker;
    private Variable constructedVariable;
    private boolean inIfNode = false;

    public QLLoader(){
        this.formNode = new FormNode();
    }
    @Override
    public void enterFormBuilder(FormParser.FormBuilderContext ctx) {
        this.formNode.setFormIdentifier(ctx.CHARACTERS().getText());
    }
    @Override
    public void enterFormData(FormParser.FormDataContext ctx) {
    }
    @Override
    public void exitFormData(FormParser.FormDataContext ctx){
        this.qlChecker = new QLChecker(formNode);
        this.qlChecker.doChecks();
    }

    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {
        System.out.println("Entering if");
        IfASTNode ifASTNode = new IfASTNode(false);
        this.inIfNode = true;
        BooleanVariable v = null;
        Condition c = null;
        for(int i=0; i< ctx.statementBlockStructure().conditions().condition().size(); i++){
            FormParser.ConditionContext cc = ctx.statementBlockStructure().conditions().condition(i);
            FormParser.BooleanOperatorContext bo = ctx.statementBlockStructure().conditions().booleanOperator(i);
            if (cc.value() instanceof FormParser.ValueContext){
                v = (BooleanVariable) this.formNode.getVariableFromList(cc.value().getText());
                this.formNode.getReferencedVariables().add(v);
            }
            if (cc.expression() instanceof FormParser.ExpressionContext){
                v = new BooleanVariable(null);
                v.setValue(this.getBooleanExpressionValue(cc.expression()));
            }
            if(bo != null){
                c = new Condition(v, bo.getText());
            }else{
                c = new Condition(v, null);
            }
            ifASTNode.addCondition(c);
        }
        this.formNode.addIfNode(ifASTNode);

     }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        System.out.println("Exit if");
        this.inIfNode = false;
    }
    @Override
    public void enterQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx) {
        constructedVariable = null;
        switch(ctx.variableType().getText()) {
            case "money":
                constructedVariable = new MoneyVariable(ctx.variable().getText());
                break;
            case "boolean":
                constructedVariable = new BooleanVariable(ctx.variable().getText());
                break;
            case "string":
                constructedVariable = new StringVariable(ctx.variable().getText());
                break;
            default:
        }
    }
    @Override
    public void exitQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx) {
        String questionText = ctx.label().getText();

        QuestionASTNode q = new QuestionASTNode(questionText, constructedVariable, !this.inIfNode);
        if(this.inIfNode) {
            this.formNode.addToLastIf(q);
            return;
        }
        this.formNode.addQuestion(new QuestionASTNode(questionText, constructedVariable, true));
    }
    @Override
    public void enterVariableValue(FormParser.VariableValueContext ctx){
        if(ctx.expression() instanceof FormParser.ExpressionContext){
            if(ctx.expression().arithmeticExpression() instanceof FormParser.ArithmeticExpressionContext){
                constructedVariable.setValue(getArithmeticExpressionValue(ctx.expression()));
            }else if(ctx.expression().booleanExpression() instanceof FormParser.BooleanExpressionContext){
                constructedVariable.setValue(getBooleanExpressionValue(ctx.expression()));
            }
        }
    }
    public FormNode getFormNode() {
        return formNode;
    }

    private BooleanExpressionValue getBooleanExpressionValue(FormParser.ExpressionContext ec){
        String operator = null;
        Variable left = null;
        Variable right = null;
        if (ec.booleanExpression().eqExpression() instanceof FormParser.EqExpressionContext){
            left = this.formNode.getVariableFromList(ec.booleanExpression().eqExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.booleanExpression().eqExpression().variable(1).getText());
            operator = "==";
        }
        if (ec.booleanExpression().neqExpression() instanceof FormParser.NeqExpressionContext){
            left = this.formNode.getVariableFromList(ec.booleanExpression().neqExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.booleanExpression().neqExpression().variable(1).getText());
            operator = "!=";
        }
        if (ec.booleanExpression().gteoqExpression() instanceof FormParser.GteoqExpressionContext){
            left = this.formNode.getVariableFromList(ec.booleanExpression().gteoqExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.booleanExpression().gteoqExpression().variable(1).getText());
            operator = ">=";
        }
        if (ec.booleanExpression().gtExpression() instanceof FormParser.GtExpressionContext){
            left = this.formNode.getVariableFromList(ec.booleanExpression().gtExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.booleanExpression().gtExpression().variable(1).getText());
            operator = ">";
        }
        if (ec.booleanExpression().stoeqExpression() instanceof FormParser.StoeqExpressionContext){
            left = this.formNode.getVariableFromList(ec.booleanExpression().stoeqExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.booleanExpression().stoeqExpression().variable(1).getText());
            operator = "<=";
        }
        if (ec.booleanExpression().stExpression() instanceof FormParser.StExpressionContext){
            left = this.formNode.getVariableFromList(ec.booleanExpression().stExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.booleanExpression().stExpression().variable(1).getText());
            operator = "<";
        }
        this.formNode.getReferencedVariables().add(left);
        this.formNode.getReferencedVariables().add(right);
        return (new BooleanExpressionValue(left, right, operator));
    }

    private ArithmeticExpressionValue getArithmeticExpressionValue(FormParser.ExpressionContext ec){
        String operator = null;
        Variable left = null;
        Variable right = null;
        if (ec.arithmeticExpression().divExpression() instanceof FormParser.DivExpressionContext){
            left = this.formNode.getVariableFromList(ec.arithmeticExpression().divExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.arithmeticExpression().divExpression().variable(1).getText());
            operator = "/";
        }
        if (ec.arithmeticExpression().mulExpression() instanceof FormParser.MulExpressionContext){
            left = this.formNode.getVariableFromList(ec.arithmeticExpression().mulExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.arithmeticExpression().mulExpression().variable(1).getText());
            operator = "*";
        }
        if (ec.arithmeticExpression().minExpression() instanceof FormParser.MinExpressionContext){
            left = this.formNode.getVariableFromList(ec.arithmeticExpression().minExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.arithmeticExpression().minExpression().variable(1).getText());
            operator = "-";
        }
        if (ec.arithmeticExpression().addExpression() instanceof FormParser.AddExpressionContext){
            left = this.formNode.getVariableFromList(ec.arithmeticExpression().addExpression().variable(0).getText());
            right = this.formNode.getVariableFromList(ec.arithmeticExpression().addExpression().variable(1).getText());
            operator = "+";
        }
        this.formNode.getReferencedVariables().add(left);
        this.formNode.getReferencedVariables().add(right);
        return (new ArithmeticExpressionValue(left, right, operator));
    }

}
