package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.FormNode;
import domain.model.expression.BooleanExpression;
import domain.model.Condition;
import domain.model.expression.Expression;
import domain.model.question.QuestionStructure;
import domain.model.PlainValue;
import domain.model.question.QuestionVariable;
import domain.model.question.QuestionVariableType;
import domain.model.question.QuestionVariableValue;

import java.util.ArrayList;
import java.util.List;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;
    private List<Condition> conditionsHolder;
    private QLChecker qlChecker;

    public QLLoader(){
        this.formNode = new FormNode();
        this.conditionsHolder = new ArrayList<Condition>();
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
                c = this.formNode.getFormData().getQuestionVariableByLabel(cc.questionVariable().getText());
                this.conditionsHolder.add((QuestionVariable) c);
                this.formNode.getFormData().getReferencedVariables().add((QuestionVariable) c);
            }
            if (cc.booleanExpression() != null){
                FormParser.BooleanExpressionContext bec = cc.booleanExpression();
                QuestionVariable leftHandOperator = this.formNode.getFormData().getQuestionVariableByLabel(bec.questionVariable(0).getText());
                QuestionVariable rightHandOperator = this.formNode.getFormData().getQuestionVariableByLabel(bec.questionVariable(1).getText());
                c = new BooleanExpression(leftHandOperator, rightHandOperator, bec.comparisonOperator().getText());
                this.conditionsHolder.add((BooleanExpression) c);
                this.formNode.getFormData().getReferencedVariables().add(leftHandOperator);
                this.formNode.getFormData().getReferencedVariables().add(rightHandOperator);
            }
            c = null;
        }
        this.formNode.getFormData().addConditionsAsKey(this.conditionsHolder);
    }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.conditionsHolder = new ArrayList<Condition>(); ;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
        this.formNode.getFormData().addQuestion(this.conditionsHolder,
            this.newQuestion(ctx.questionLabel().getText(), ctx.questionVariable().getText(), ctx.questionVariableType().getText(), ctx.questionVariableValue()));
    }
    private QuestionStructure newQuestion(String label, String questionVariable, String questionVariableType, FormParser.QuestionVariableValueContext questionVariableValue){

        QuestionVariableValue constructedQuestionVariableValue = null;
        QuestionVariableType constructedQuestionVariableType = null;
        switch(questionVariableType){
            case "boolean":
                constructedQuestionVariableType = QuestionVariableType.BOOLEAN;
                break;
            case "money":
                constructedQuestionVariableType = QuestionVariableType.MONEY;
                break;
            case "string":
                constructedQuestionVariableType = QuestionVariableType.STRING;
                break;
        }
        if(questionVariableValue != null && questionVariableValue.expression() != null){
            FormParser.ExpressionContext ec = questionVariableValue.expression();
            QuestionVariable leftHandOperator = this.formNode.getFormData().getQuestionVariableByLabel(ec.questionVariable(0).getText());
            QuestionVariable rightHandOperator = this.formNode.getFormData().getQuestionVariableByLabel(ec.questionVariable(1).getText());
            constructedQuestionVariableValue = new Expression(leftHandOperator, rightHandOperator, ec.operator().getText());
        }
        if(questionVariableValue != null && questionVariableValue.plainValue() != null){
            FormParser.PlainValueContext vc = questionVariableValue.plainValue();
            constructedQuestionVariableValue = new PlainValue(vc.getText());
        }

        QuestionVariable constructedQuestionVariable = new QuestionVariable(questionVariable, constructedQuestionVariableType, constructedQuestionVariableValue);
        return new QuestionStructure(label, constructedQuestionVariable);
    }
    public FormNode getFormNode() {
        return formNode;
    }


}
