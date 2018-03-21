package org.uva.sea.gui.newImpl.model.factory;

import org.uva.sea.gui.newImpl.widget.Widget;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class WidgetFactory {

    private QLWidgetFactory qlWidgetFactory = new QLWidgetFactory();

    private QLSWidgetFactory qlsWidgetFactory = new QLSWidgetFactory();

    public Widget createWidget(QuestionData questionData) {
        if( questionData.getWidgetType() == WidgetType.DEFAULT)
            return this.qlWidgetFactory.createWidget(questionData.getNodeType(), questionData);
        else
            return this.qlsWidgetFactory.createWidget(questionData.getWidgetType(), questionData);
    }

}
