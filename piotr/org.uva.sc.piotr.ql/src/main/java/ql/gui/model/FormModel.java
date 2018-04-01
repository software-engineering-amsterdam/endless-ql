package ql.gui.model;

import ql.ast.model.Form;
import ql.gui.controller.FormController;
import ql.logic.collectors.CollectQuestionModelsVisitor;

import java.util.List;

public class FormModel {
    private final List<QuestionModel> questionModels;

    public FormModel(Form form) {
        CollectQuestionModelsVisitor collectQuestionModelsVisitor = new CollectQuestionModelsVisitor();
        this.questionModels = collectQuestionModelsVisitor.getQuestionModels(form);
    }

    public FormModel(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }

    public List<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void registerController(FormController formController) {
        for (QuestionModel questionModel : this.questionModels) {
            questionModel.registerController(formController);
        }
    }
}
