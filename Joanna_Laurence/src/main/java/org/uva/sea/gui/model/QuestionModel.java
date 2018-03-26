package org.uva.sea.gui.model;

import org.uva.sea.gui.controller.IQuestionValueUpdatedListener;
import org.uva.sea.gui.model.factory.IWidgetFactory;
import org.uva.sea.gui.model.factory.WidgetNotFoundException;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class QuestionModel {

    private final IQuestionValueUpdatedListener questionValueUpdatedListener;

    private final IWidgetFactory widgetFactory;

    private BaseEvaluator baseEvaluator = null;

    public QuestionModel(final IQuestionValueUpdatedListener questionValueUpdateListener, final IWidgetFactory widgetFactory) {
        this.questionValueUpdatedListener = questionValueUpdateListener;
        this.widgetFactory = widgetFactory;
    }

    public final void setBaseEvaluator(final BaseEvaluator baseEvaluator) {
        this.baseEvaluator = baseEvaluator;
    }

    public final RenderingElements getQuestionRenders(final EvaluationResult evaluationResult) throws WidgetNotFoundException {
        if (evaluationResult == null)
            return null;

        final Collection<BaseWidget> guiElements = new ArrayList<>();
        for (final QuestionData questionData : evaluationResult.getQuestions())
            guiElements.add(this.createWidget(questionData));

        return new RenderingElements(guiElements, evaluationResult.getMessages());
    }

    public final EvaluationResult getEvaluationResults() throws IOException, InterruptedException {
        if (this.baseEvaluator == null)
            return null;

        return this.baseEvaluator.evaluate();
    }

    private BaseWidget createWidget(final QuestionData questionData) throws WidgetNotFoundException {
        return this.widgetFactory.createWidget(questionData, this.questionValueUpdatedListener);
    }

    public final void setVariable(final String identifier, final Value value) {
        this.baseEvaluator.setVariable(identifier, value);
    }
}
