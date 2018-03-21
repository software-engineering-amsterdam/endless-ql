package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.factory.visitor.DefaultWidgetFromValueVisitor;

public class DefaultWidgetFactory extends AbstractWidgetFactory {

    private final FormController controller;

    public DefaultWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return questionModel.accept(new DefaultWidgetFromValueVisitor(this.controller));
    }
}