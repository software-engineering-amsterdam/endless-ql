package loader;

import antlr.ql.FormBaseListener;
import antlr.ql.FormParser;
import domain.FormNode;
import domain.model.IfASTNode;
import domain.model.value.ArithmeticExpressionValue;
import domain.model.value.BooleanExpressionValue;
import domain.model.variable.*;
import domain.model.QuestionASTNode;

import java.util.ArrayList;
import java.util.List;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;

    private List<Variable> conditionsHolder;
    private QLChecker qlChecker;
    private Variable constructedVariable;
    private boolean inIfNode = false;

    public QLLoader(){
        this.formNode = new FormNode();
        this.conditionsHolder = new ArrayList<Variable>();
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
        this.formNode.addIfNode(new IfASTNode(false));
        this.inIfNode = true;
        Object c = null;
        for(FormParser.ConditionContext cc : ctx.statementBlockStructure().conditions().condition()){
            System.out.println(cc.value().getText());
            if (cc.value() instanceof FormParser.ValueContext){
                c = this.formNode.getVariableFromList(cc.value().getText());
//                this.conditionsHolder.add((BooleanVariable) c);
//                this.formNode.getReferencedVariables().add((Variable) c);
            }
            c = null;
        }
//        this.formNode.addConditionsAsKey(conditionsHolder);
     }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        System.out.println("Exit if");
        this.inIfNode = false;
        this.conditionsHolder = new ArrayList<Variable>(); ;
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
                constructedVariable.setValue(getArithmeticExpression(ctx.expression()));
            }else if(ctx.expression().booleanExpression() instanceof FormParser.BooleanExpressionContext){
                constructedVariable.setValue(getBooleanExpression(ctx.expression()));
            }
        }
    }
    public FormNode getFormNode() {
        return formNode;
    }

    private BooleanExpressionValue getBooleanExpression(FormParser.ExpressionContext ec){
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

    private ArithmeticExpressionValue getArithmeticExpression(FormParser.ExpressionContext ec){
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
