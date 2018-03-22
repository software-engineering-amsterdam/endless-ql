package org.uva.sea.gui.ql.model.factory;

import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public interface WidgetFactory {
    Widget createWidget(QuestionData questionData, IGuiElementUpdateListener listener);
}
