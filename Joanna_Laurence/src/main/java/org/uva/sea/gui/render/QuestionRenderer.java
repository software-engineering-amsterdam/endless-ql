package org.uva.sea.gui.render;

import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.model.QuestionModelFactoryImpl;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;

import java.util.ArrayList;
import java.util.List;

public class QuestionRenderer implements Renderable<List<QuestionData>> {

    private JavafxRendererVisitor renderer;

    public QuestionRenderer(JavafxRendererVisitor renderer) {
        this.renderer = renderer;
    }

    public void render(List<QuestionData> questionData) {
        List<BaseQuestionModel> questionGUIs = getBaseQuestionModels(questionData);
        renderer.displayQuestions(questionGUIs);
    }

    private List<BaseQuestionModel> getBaseQuestionModels(List<QuestionData> data) {
        QuestionModelFactoryImpl factory = new QuestionModelFactoryImpl();
        List<BaseQuestionModel> questionGUIs = new ArrayList<>();
        for (QuestionData question : data) {
            BaseQuestionModel questionRow = factory.create(question);
            questionGUIs.add(questionRow);
        }
        return questionGUIs;
    }
}
