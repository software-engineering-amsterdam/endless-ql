package ql.gui.model;

import ql.gui.controller.FormController;

import java.util.List;

public class FormModel {
    private final List<QuestionModel> questionModels;

    public FormModel(List<QuestionModel> questionModels) {
        this.questionModels = questionModels;
        System.out.println("done");
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
