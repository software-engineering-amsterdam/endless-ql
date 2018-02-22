package loader;

import antlr.FormBaseListener;
import antlr.FormParser;
import domain.FormNode;
import domain.Question;

public class QLLoader extends FormBaseListener {
    private FormNode formNode;
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
            this.formNode.getFormData().addPlainQuestion(new Question(qsc.questionLabel().getText(), qsc.questionVariable().getText(), qsc.questionVariableType().getText()));
        }
    }
    @Override
    public void enterIfStructure(FormParser.IfStructureContext ctx) {
        this.formNode.getFormData().addQuestionVariableToConditionQuestions(ctx.statementBlockStructure().questionVariable().getText());
        for (FormParser.QuestionStructureContext qsc : ctx.questionStructure()){
            this.formNode.getFormData().addConditionQuestion(ctx.statementBlockStructure().questionVariable().getText(), new Question(qsc.questionLabel().getText(), qsc.questionVariable().getText(), qsc.questionVariableType().getText()));
        }
    }

    public FormNode getFormNode() {
        return formNode;
    }
}
