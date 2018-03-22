package org.uva.sea.gui.model;

import org.uva.sea.gui.controller.IGuiElementUpdateListener;
import org.uva.sea.gui.model.factory.IWidgetFactory;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class QuestionModel {
    private final IGuiElementUpdateListener questionValueUpdateListener;
    private BaseEvaluator interpreter = null;
    private final IWidgetFactory widgetFactory;

    public QuestionModel(IGuiElementUpdateListener questionValueUpdateListener, IWidgetFactory widgetFactory) {
        this.questionValueUpdateListener = questionValueUpdateListener;
        this.widgetFactory = widgetFactory;
    }

    public void setInterpreter(BaseEvaluator interpreter) {
        this.interpreter = interpreter;
    }

    public RenderElements getQuestionRenders(EvaluationResult interpreterResult) {
        if (interpreterResult == null)
            return null;

        Collection<BaseWidget> guiElements = new ArrayList<>();
        for (QuestionData questionData : interpreterResult.getQuestions())
            guiElements.add(this.createWidget(questionData));

        return new RenderElements(guiElements, interpreterResult.getMessages());
    }

    public EvaluationResult getEvaluationResults() throws IOException, InterruptedException {
        if (this.interpreter == null)
            return null;

        return this.interpreter.getQuestions();
    }

    private BaseWidget createWidget(QuestionData questionData) {
        return this.widgetFactory.createWidget(questionData, this.questionValueUpdateListener);
    }

    public void setVariable(String identifier, Value value) {
        this.interpreter.setVariable(identifier, value);
    }
}
