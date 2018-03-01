package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.FormNode;
import domain.model.BooleanExpression;
import domain.model.Condition;
import domain.model.Expression;
import domain.model.question.QuestionStructure;
import domain.model.PlainValue;
import domain.model.question.QuestionVariable;
import domain.model.question.QuestionVariableType;
import domain.model.question.QuestionVariableValue;

import java.util.ArrayList;
import java.util.List;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;
    private List<Condition> ifStatementBlockConditionsHolder;
    private QLChecker qlChecker;

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
        this.ifStatementBlockConditionsHolder = new ArrayList<Condition>();
        Object c = null;
        for(FormParser.ConditionContext cc : ctx.statementBlockStructure().conditions().condition()){
            if (cc.questionVariable() != null){
                c = this.formNode.getFormData().getQuestionVariableByLabel(cc.questionVariable().getText());
                this.ifStatementBlockConditionsHolder.add((QuestionVariable) c);
                this.formNode.getFormData().getReferencedVariables().add((QuestionVariable) c);
            }
            if (cc.booleanExpression() != null){
                FormParser.BooleanExpressionContext bec = cc.booleanExpression();
                QuestionVariable leftHandOperator = this.formNode.getFormData().getQuestionVariableByLabel(bec.questionVariable(0).getText());
                QuestionVariable rightHandOperator = this.formNode.getFormData().getQuestionVariableByLabel(bec.questionVariable(1).getText());
                c = new BooleanExpression(leftHandOperator, rightHandOperator, bec.comparisonOperator().getText());
                this.ifStatementBlockConditionsHolder.add((BooleanExpression) c);
                this.formNode.getFormData().getReferencedVariables().add(leftHandOperator);
                this.formNode.getFormData().getReferencedVariables().add(rightHandOperator);
            }
            c = null;
        }
        this.formNode.getFormData().addConditionsAsKey(this.ifStatementBlockConditionsHolder);
    }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.ifStatementBlockConditionsHolder = null;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
        switch (ctx.getParent().invokingState){
            case 37:
                this.formNode.getFormData().addPlainQuestion(
                    this.newQuestion(ctx.questionLabel().getText(), ctx.questionVariable().getText(), ctx.questionVariableType().getText(), ctx.questionVariableValue()));
                break;
            case 45:
                this.formNode.getFormData().addConditionQuestion(this.ifStatementBlockConditionsHolder,
                    this.newQuestion(ctx.questionLabel().getText(), ctx.questionVariable().getText(), ctx.questionVariableType().getText(), ctx.questionVariableValue()));
                break;
            default:
        }
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
