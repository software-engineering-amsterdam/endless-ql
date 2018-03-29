package gui.model;

import gui.elements.LabelWithWidget;
import gui.render.GUIController;
import gui.render.WidgetStyleApplier;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import qls.model.statement.DefaultStyle;
import qls.model.widget.Widget;
import qls.model.widget.WidgetType;

import java.util.List;

public class GUIQuestionWithStyling extends GUIElement implements IGUIQuestion {

    private final GUIQuestion guiQuestion;
    private final List<DefaultStyle> defaultStyles;
    private final Widget widget;

    public GUIQuestionWithStyling(GUIQuestion guiQuestion, List<DefaultStyle> defaultStyles, Widget widget) {
        this.guiQuestion = guiQuestion;
        this.defaultStyles = defaultStyles;
        this.widget = widget;
    }

    @Override
    public LabelWithWidget render(GUIController guiController) {
        GUIWidget guiWidget = WidgetFactory.getWidget(this.guiQuestion.getType(), widget);
        LabelWithWidget parent = this.guiQuestion.render(guiWidget, guiController);

        WidgetStyleApplier widgetStyleApplier = new WidgetStyleApplier();
        widgetStyleApplier.applyStyles(parent, this.defaultStyles, this.guiQuestion.getType());

        return parent;
    }
}
