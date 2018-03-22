package org.uva.sea.gui.model;

import org.uva.sea.gui.IGuiElementUpdateListener;
import org.uva.sea.gui.components.GuiMessage;
import org.uva.sea.gui.components.Renderable;
import org.uva.sea.gui.model.factory.WidgetFactory;
import org.uva.sea.gui.widget.Widget;
import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class QuestionModel {
    private IGuiElementUpdateListener questionValueUpdateListener;
    private BaseEvaluator interpreter = null;
    private WidgetFactory widgetFactory = new WidgetFactory();

    public QuestionModel(IGuiElementUpdateListener questionValueUpdateListener) {
        this.questionValueUpdateListener = questionValueUpdateListener;
    }

    public void setInterpreter(BaseEvaluator interpreter) {
        this.interpreter = interpreter;
    }

    public Collection<Renderable> getQuestionRenders() throws IOException, InterruptedException {
        Collection<Renderable> guiElements = new ArrayList<>();
        EvaluationResult interpreterResult = this.getEvaluationResults();

        if (interpreterResult == null)
            return new ArrayList<>();

        Messages warnings = interpreterResult.getMessages();
        for (String warning : warnings.getMessage(MessageTypes.WARNING))
            guiElements.add(new GuiMessage(warning));

        for (QuestionData questionData : interpreterResult.getQuestions())
            guiElements.add(this.createWidget(questionData));

        return guiElements;
    }

    public EvaluationResult getEvaluationResults() throws IOException, InterruptedException {
        if (this.interpreter == null)
            return null;

        return this.interpreter.getQuestions();
    }

    private Widget createWidget(QuestionData questionData) {
        return this.widgetFactory.createWidget(questionData, this.questionValueUpdateListener);
    }

    public void setVariable(String identifier, Value value) {
        this.interpreter.setVariable(identifier, value);
    }
}
