package gui.model;

import gui.components.LabelWithWidget;
import gui.components.widgets.GUIWidget;
import gui.render.GUIController;
import gui.render.WidgetFactory;
import gui.render.WidgetStyleApplier;
import qls.model.statement.DefaultStyle;
import qls.model.widget.Widget;

import java.util.List;

public class GUIQuestionWithStyling extends GUIElement {

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
        LabelWithWidget labelWithWidget = this.guiQuestion.render(guiWidget, guiController);

        WidgetStyleApplier widgetStyleApplier = new WidgetStyleApplier();
        widgetStyleApplier.applyStyles(labelWithWidget, this.defaultStyles, this.guiQuestion.getType());

        return labelWithWidget;
    }
}
