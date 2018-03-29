package gui.model;

import gui.elements.LabelWithWidget;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import javafx.beans.InvalidationListener;
import ql.evaluation.SymbolTable;
import ql.model.expression.ReturnType;
import qls.model.statement.DefaultStyle;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;
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
    public LabelWithWidget render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        System.out.println("render[" + guiQuestion.getIdentifier() + "][" + widgetType + "]");
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(guiQuestion.getType(), widgetType);
        LabelWithWidget parent = guiQuestion.render(guiWidget, symbolTable, allWidgetsListener);

        // Apply styles to this widget where applicable
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.getType() == guiQuestion.getType()) {
                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
                    parent.apply(styleAttribute);
                }
            }
        }

        return parent;
    }
}
