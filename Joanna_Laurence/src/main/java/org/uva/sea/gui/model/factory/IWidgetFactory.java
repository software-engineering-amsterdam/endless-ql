package org.uva.sea.gui.model.factory;

import org.uva.sea.gui.controller.IGuiElementUpdateListener;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public interface IWidgetFactory {
    BaseWidget createWidget(QuestionData questionData, IGuiElementUpdateListener listener);
}
