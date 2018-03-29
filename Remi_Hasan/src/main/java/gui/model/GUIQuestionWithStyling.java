package gui.model;

import gui.render.GUIController;
import gui.elements.LabelWithWidget;
import gui.render.WidgetStyleApplier;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import qls.model.statement.DefaultStyle;
import qls.model.style.StyleAttribute;

import java.util.List;

public class GUIQuestionWithStyling extends GUIElement implements IGUIQuestion {

    private final GUIQuestion guiQuestion;
    private final List<DefaultStyle> defaultStyles;

    public GUIQuestionWithStyling(GUIQuestion guiQuestion, List<DefaultStyle> defaultStyles) {
        this.guiQuestion = guiQuestion;
        this.defaultStyles = defaultStyles;
    }

    @Override
    public LabelWithWidget render(GUIController guiController) {
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.guiQuestion.getType());
        LabelWithWidget labelWithWidget = this.guiQuestion.render(guiWidget, guiController);

        WidgetStyleApplier widgetStyleApplier = new WidgetStyleApplier();
        widgetStyleApplier.applyStyles(this.guiQuestion.getType(), labelWithWidget, this.defaultStyles);

        return labelWithWidget;
    }
}
