package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.model.Expression;
import domain.FormNode;
import domain.model.Question;
import domain.model.Value;
import domain.model.Variable;


public class QLLoader extends FormBaseListener {
    private FormNode formNode;
    private String ifStatementBlockVariableHolder;
    public QLLoader(){
        this.formNode = new FormNode();
    }
    @Override
    public void enterFormBuilder(FormParser.FormBuilderContext ctx) {
        this.formNode.setFormIdentifier(ctx.CHARACTERS().getText());
    }
    @Override
    public void enterFormData(FormParser.FormDataContext ctx) {
        for (FormParser.QuestionStructureContext qsc : ctx.questionStructure()){

        }
    }
    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {
        this.formNode.getFormData().addQuestionVariableToConditionQuestions(ctx.statementBlockStructure().questionVariable().getText());
        this.ifStatementBlockVariableHolder = ctx.statementBlockStructure().questionVariable().getText();
    }
    @Override
    public void exitIfStructure(FormParser.IfStructureContext ctx){
        this.ifStatementBlockVariableHolder = null;
    }
    @Override
    public void enterQuestionStructure(FormParser.QuestionStructureContext ctx) {
        System.out.println(ctx.getParent().invokingState);
        switch (ctx.getParent().invokingState){
            case 27:
                this.formNode.getFormData().addPlainQuestion(
                        this.newQuestion(ctx.questionLabel().getText(), ctx.questionVariable().getText(), ctx.questionVariableType().getText(), ctx.questionVariableValue()));
                break;
            case 35:
                this.formNode.getFormData().addConditionQuestion(this.ifStatementBlockVariableHolder,
                        this.newQuestion(ctx.questionLabel().getText(), ctx.questionVariable().getText(), ctx.questionVariableType().getText(), ctx.questionVariableValue()));
                break;
            default:
        }
    }
    private Question newQuestion(String label, String variable, String variableType, FormParser.QuestionVariableValueContext variableValue){
        Variable constructedVariable = null;

        if(variableValue != null && variableValue.expression() != null){
            FormParser.ExpressionContext ec = variableValue.expression();
            constructedVariable = new Expression(ec.questionVariable(0).getText(), ec.questionVariable(1).getText(), ec.operator().getText());
        }
        if(variableValue != null && variableValue.value() != null){
            FormParser.ValueContext vc = variableValue.value();
            constructedVariable = new Value(vc.getText());
        }
        return new Question(label, variable, variableType, constructedVariable);
    }
    public FormNode getFormNode() {
        return formNode;
    }
}
