package org.uva.sea.gui.newImpl.model;

import org.uva.sea.gui.newImpl.components.GuiMessage;
import org.uva.sea.gui.newImpl.components.Renderable;
import org.uva.sea.gui.newImpl.model.factory.WidgetFactory;
import org.uva.sea.gui.newImpl.widget.Widget;
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
    private BaseEvaluator interpreter = null;
    private WidgetFactory widgetFactory = new WidgetFactory();

    public void setInterpreter(BaseEvaluator interpreter) {
        this.interpreter = interpreter;
    }

    public Collection<Renderable> getQuestionRenders() {
        if(this.interpreter == null)
            return new ArrayList<>();

        Collection<Renderable> guiElements = new ArrayList<>();
        try {
            EvaluationResult interpreterResult = this.interpreter.getQuestions();

            Messages warnings = interpreterResult.getMessages();
            for (String warning : warnings.getMessage(MessageTypes.WARNING))
                guiElements.add(new GuiMessage(warning));

            for(QuestionData questionData : interpreterResult.getQuestions())
                guiElements.add(this.createWidget(questionData));

        } catch (InterruptedException | IOException e) {
            guiElements.add(new GuiMessage("Warning: Error: " + e.getMessage()));
        }

        return guiElements;
    }

    private Widget createWidget(QuestionData questionData) {
        return this.widgetFactory.createWidget(questionData);
    }

    public void setVariable(String identifier, Value value) {
        this.interpreter.setVariable(identifier, value);
    }
}
