package loader;

import antlr.ql.FormBaseListener;
import antlr.ql.FormParser;
import domain.FormNode;
import domain.model.IfASTNode;
import domain.model.value.ExpressionValue;
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
        this.formNode.getFormData().addConditionsAsKey(conditionsHolder);
    }
    @Override
    public void exitFormData(FormParser.FormDataContext ctx){
        this.qlChecker = new QLChecker(formNode);
        //this.qlChecker.doChecks();
    }

    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {

        System.out.println("Entering if");
        this.formNode.addIfNode(new IfASTNode(false));
        this.inIfNode = true;
//        Object c = null;
//        for(FormParser.ConditionContext cc : ctx.statementBlockStructure().conditions().condition()){
//            if (cc.value() instanceof FormParser.ValueContext){
//                c = this.formNode.getFormData().getVariableByLabel(cc.value().getText());
//                this.conditionsHolder.add((BooleanVariable) c);
//                this.formNode.getFormData().getReferencedVariables().add((Variable) c);
//            }
//            c = null;
//        }
//        this.formNode.getFormData().addConditionsAsKey(conditionsHolder);
     }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        System.out.println("Exit if");
        this.inIfNode = false;

        this.conditionsHolder = new ArrayList<Variable>(); ;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
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
    public void exitQuestionStructure(FormParser.QuestionStructureContext ctx) {
        String questionText = ctx.label().getText();

        this.formNode.getFormData().addQuestion(this.conditionsHolder, new QuestionASTNode(questionText, constructedVariable, true));
        QuestionASTNode q = new QuestionASTNode(questionText, constructedVariable, !this.inIfNode);
        if(this.inIfNode) {
            this.formNode.addToLastIf(q);
            System.out.println("  Q: " + questionText);
            return;
        }

        this.formNode.addQuestion(q);
        System.out.println("Q: " + questionText);
    }
    @Override
    public void enterVariableValue(FormParser.VariableValueContext ctx){
        if(ctx.expression() instanceof FormParser.ExpressionContext){
            constructedVariable.setValue(getExpressionByContext(ctx.expression()));
        }
    }
    public FormNode getFormNode() {
        return formNode;
    }

    public ExpressionValue getExpressionByContext(FormParser.ExpressionContext ec) {
        FormParser.ExpressionContext fe = ec;
        String operator = null;
        Variable left = null;
        Variable right = null;
        if (fe.aritmaticExpression().divExpression() instanceof FormParser.DivExpressionContext){
            left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().divExpression().variable(0).getText());
            right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().divExpression().variable(1).getText());
            operator = "/";
        }
        if (fe.aritmaticExpression().mulExpression() instanceof FormParser.MulExpressionContext){
            left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().mulExpression().variable(0).getText());
            right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().mulExpression().variable(1).getText());
            operator = "*";
        }
        if (fe.aritmaticExpression().minExpression() instanceof FormParser.MinExpressionContext){
            left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().minExpression().variable(0).getText());
            right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().minExpression().variable(1).getText());
            operator = "-";
        }
        if (fe.aritmaticExpression().addExpression() instanceof FormParser.AddExpressionContext){
            left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().addExpression().variable(0).getText());
            right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().addExpression().variable(1).getText());
            operator = "+";
        }
        this.formNode.getFormData().getReferencedVariables().add(left);
        this.formNode.getFormData().getReferencedVariables().add(right);
        return (new ExpressionValue(left, right, operator));

    }


}
