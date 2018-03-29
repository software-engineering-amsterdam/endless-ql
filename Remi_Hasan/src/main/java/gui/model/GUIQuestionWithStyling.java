package gui.model;

import gui.elements.LabelWithWidget;
import gui.render.GUIController;
import gui.render.WidgetStyleApplier;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import qls.model.statement.DefaultStyle;
import qls.model.widget.WidgetType;

import java.util.List;

public class GUIQuestionWithStyling extends GUIElement implements IGUIQuestion {

    private final GUIQuestion guiQuestion;
    private final List<DefaultStyle> defaultStyles;
    private final WidgetType widgetType;

    public GUIQuestionWithStyling(GUIQuestion guiQuestion, List<DefaultStyle> defaultStyles, WidgetType widgetType) {
        this.guiQuestion = guiQuestion;
        this.defaultStyles = defaultStyles;
        this.widgetType = widgetType;
    }

    @Override
    public LabelWithWidget render(GUIController guiController) {
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.guiQuestion.getType(), widgetType);
        LabelWithWidget parent = this.guiQuestion.render(guiWidget, guiController);

        WidgetStyleApplier widgetStyleApplier = new WidgetStyleApplier();
        widgetStyleApplier.applyStyles(this.guiQuestion.getType(), parent, this.defaultStyles);

        return parent;
    }
}
