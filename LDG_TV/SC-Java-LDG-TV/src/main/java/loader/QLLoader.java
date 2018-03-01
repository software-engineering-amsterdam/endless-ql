package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.FormNode;
import domain.model.Expression;
import domain.model.question.QuestionStructure;
import domain.model.PlainValue;
import domain.model.question.QuestionVariable;
import domain.model.question.QuestionVariableType;
import domain.model.question.QuestionVariableValue;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;
    private String ifStatementBlockVariableHolder;
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
        for(FormParser.ConditionContext cc : ctx.statementBlockStructure().conditions().condition()){
            if (cc.questionVariable() != null){
                this.formNode.getFormData().getReferencedVariables().add(cc.questionVariable().getText());
            }
            if (cc.booleanExpression() != null){
                for (FormParser.QuestionVariableContext qc : cc.booleanExpression().questionVariable()){
                    this.formNode.getFormData().getReferencedVariables().add(qc.getText());
                }
            }
        }
        this.formNode.getFormData().addQuestionVariableToConditionQuestions(ctx.statementBlockStructure().getText());
        this.ifStatementBlockVariableHolder = ctx.statementBlockStructure().getText();
    }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.ifStatementBlockVariableHolder = null;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
        switch (ctx.getParent().invokingState){
            case 37:
                this.formNode.getFormData().addPlainQuestion(
                    this.newQuestion(ctx.questionLabel().getText(), ctx.questionVariable().getText(), ctx.questionVariableType().getText(), ctx.questionVariableValue()));
                break;
            case 45:
                this.formNode.getFormData().addConditionQuestion(this.ifStatementBlockVariableHolder,
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
            constructedQuestionVariableValue = new Expression(ec.questionVariable(0).getText(), ec.questionVariable(1).getText(), ec.operator().getText());
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
