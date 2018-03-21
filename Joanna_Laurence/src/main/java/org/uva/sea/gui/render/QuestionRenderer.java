package org.uva.sea.gui.render;

import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.model.QuestionModelFactory;
import org.uva.sea.gui.model.QuestionModelFactoryImpl;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

import java.util.ArrayList;
import java.util.List;

public class QuestionRenderer implements Renderable<List<QuestionData>> {

    private final ViewRenderer renderer;

    public QuestionRenderer(ViewRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(List<QuestionData> questionData) {
        List<BaseQuestionModel> questionGUIs = this.getBaseQuestionModels(questionData);
        this.renderer.displayQuestions(questionGUIs);
    }

    private List<BaseQuestionModel> getBaseQuestionModels(Iterable<QuestionData> data) {
        QuestionModelFactory factory = new QuestionModelFactoryImpl();
        List<BaseQuestionModel> questionGUIs = new ArrayList<>();
        for (QuestionData question : data) {
            BaseQuestionModel questionRow = factory.create(question);
            questionGUIs.add(questionRow);
        }
        return questionGUIs;
    }
}
