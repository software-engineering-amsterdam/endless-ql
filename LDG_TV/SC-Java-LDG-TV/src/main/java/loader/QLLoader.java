package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.FormNode;
import domain.model.expression.Expression;
import domain.model.variable.*;
import domain.model.Question;

import java.util.ArrayList;
import java.util.List;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;
    private List<Variable> conditionsHolder;
    private QLChecker qlChecker;
    private Variable constructedVariable;
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
        this.qlChecker.doChecks();
    }

    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {
        Object c = null;
        for(FormParser.ConditionContext cc : ctx.statementBlockStructure().conditions().condition()){
            if (cc.value() instanceof FormParser.ValueContext){
                c = this.formNode.getFormData().getVariableByLabel(cc.value().getText());
                this.conditionsHolder.add((BooleanVariable) c);
                this.formNode.getFormData().getReferencedVariables().add((Variable) c);
            }
            c = null;
        }
        this.formNode.getFormData().addConditionsAsKey(conditionsHolder);
     }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.conditionsHolder = new ArrayList<Variable>(); ;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
        constructedVariable = null;
    }
    @Override
    public void exitQuestionStructure(FormParser.QuestionStructureContext ctx) {
        this.formNode.getFormData().addQuestion(this.conditionsHolder, new Question(ctx.label().getText(), constructedVariable));
    }
    @Override
    public void enterVariableType(FormParser.VariableTypeContext ctx){
        switch(ctx.getText()) {
            case "money":
                constructedVariable = new MoneyVariable(ctx.getText(), null);
                break;
            case "boolean":
                constructedVariable = new BooleanVariable(ctx.getText(), null);
                break;
            case "string":
                constructedVariable = new StringVariable(ctx.getText(), null);
                break;
        }
    }
    @Override
    public void enterVariableValue(FormParser.VariableValueContext ctx){
        if(ctx.value() instanceof FormParser.ValueContext) {
            constructedVariable.setValue(new PlainValue(ctx.getText()));
        }else if(ctx.expression() instanceof FormParser.ExpressionContext){
            constructedVariable.setValue(getExpressionByContext(ctx.expression()));
        }
    }
    public FormNode getFormNode() {
        return formNode;
    }

    public Expression getExpressionByContext(FormParser.ExpressionContext ec) {
            FormParser.ExpressionContext fe = ec;
            if (fe.aritmaticExpression().divExpression() instanceof FormParser.DivExpressionContext){
                Variable left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().divExpression().variable(0).getText());
                Variable right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().divExpression().variable(1).getText());
                return (new Expression(left, right, "/"));
            }
            if (fe.aritmaticExpression().mulExpression() instanceof FormParser.MulExpressionContext){
                Variable left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().mulExpression().variable(0).getText());
                Variable right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().mulExpression().variable(1).getText());
                return (new Expression(left, right, "*"));
            }
            if (fe.aritmaticExpression().minExpression() instanceof FormParser.MinExpressionContext){
                Variable left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().minExpression().variable(0).getText());
                Variable right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().minExpression().variable(1).getText());
                return (new Expression(left, right, "-"));
            }
            if (fe.aritmaticExpression().addExpression() instanceof FormParser.AddExpressionContext){
                Variable left = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().addExpression().variable(0).getText());
                Variable right = this.formNode.getFormData().getVariableByLabel(fe.aritmaticExpression().addExpression().variable(1).getText());
                return (new Expression(left, right, "+"));
            }
            return null;
    }


}
