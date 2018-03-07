package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.FormNode;
import domain.model.variable.BooleanVariable;
import domain.model.variable.ExpressionVariable;
import domain.model.Question;
import domain.model.variable.StringVariable;
import domain.model.variable.Variable;

import java.util.ArrayList;
import java.util.List;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;
    private List<Variable> conditionsHolder;
    private QLChecker qlChecker;

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
            if (cc.questionVariable() != null){
                c = this.formNode.getFormData().getVariableByLabel(cc.questionVariable().getText());
                this.conditionsHolder.add((BooleanVariable) c);
                this.formNode.getFormData().getReferencedVariables().add((Variable) c);
            }
            if (cc.booleanExpression() != null){
                FormParser.BooleanExpressionContext bec = cc.booleanExpression();
                Variable leftHandOperator = this.formNode.getFormData().getVariableByLabel(bec.questionVariable(0).getText());
                Variable rightHandOperator = this.formNode.getFormData().getVariableByLabel(bec.questionVariable(1).getText());
                c = new ExpressionVariable(null, leftHandOperator, rightHandOperator, bec.comparisonOperator().getText());
                this.conditionsHolder.add((ExpressionVariable) c);
                this.formNode.getFormData().getReferencedVariables().add(leftHandOperator);
                this.formNode.getFormData().getReferencedVariables().add(rightHandOperator);
            }
            c = null;
        }
        this.formNode.getFormData().addConditionsAsKey(this.conditionsHolder);
     }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.conditionsHolder = new ArrayList<Variable>(); ;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
        this.formNode.getFormData().addQuestion(this.conditionsHolder, this.newQuestion(ctx.questionLabel().getText(), ctx));
        ctx.questionVariableType().getText();
    }
    private Question newQuestion(String label, FormParser.QuestionStructureContext ctx){
        Variable constructedVariable = null;
        //Values are null;
        if (ctx.questionVariableType().getText().equals("money") || ctx.questionVariableType().getText().equals("string") ){
            String stringValue = null;
            if(ctx.questionVariableValue() != null && ctx.questionVariableValue().plainValue() != null){
                stringValue = ctx.questionVariableValue().plainValue().getText();
            }
            constructedVariable = new StringVariable(ctx.questionVariable().getText(), stringValue);
        }
        if(ctx.questionVariableType().getText().equals("boolean")){
            boolean boolValue = false;
            if(ctx.questionVariableValue() != null && ctx.questionVariableValue().plainValue() != null){
                boolValue = Boolean.valueOf(ctx.questionVariableValue().plainValue().getText());
            }
            constructedVariable = new BooleanVariable(ctx.questionVariable().getText(), boolValue);
        }
        if(ctx.questionVariableValue() != null && ctx.questionVariableValue().expression() != null){
            FormParser.ExpressionContext ec = ctx.questionVariableValue().expression();
            Variable leftHandOperator = this.formNode.getFormData().getVariableByLabel(ec.questionVariable(0).getText());
            Variable rightHandOperator = this.formNode.getFormData().getVariableByLabel(ec.questionVariable(1).getText());
            constructedVariable = new ExpressionVariable(ctx.questionVariable().getText(), leftHandOperator, rightHandOperator, ec.operator().getText());
        }

        return new Question(label, constructedVariable);
    }
    public FormNode getFormNode() {
        return formNode;
    }


}
