package gui.model;

import gui.elements.LabelWithWidget;
import gui.widgets.GUIWidget;
import gui.widgets.RadioWidget;
import gui.widgets.WidgetFactory;
import javafx.beans.InvalidationListener;
import ql.evaluation.SymbolTable;
import ql.model.expression.ReturnType;
import qls.model.DefaultStyle;
import qls.model.style.StyleAttribute;

import java.util.List;

public class GUIQuestionWithStyling extends GUISectionElement implements IGUIQuestion {

    private final GUIQuestion guiQuestion;
    private final List<DefaultStyle> defaultStyles;

    public GUIQuestionWithStyling(GUIQuestion guiQuestion, List<DefaultStyle> defaultStyles) {
        this.guiQuestion = guiQuestion;
        this.defaultStyles = defaultStyles;
    }

    @Override
    public LabelWithWidget render(SymbolTable symbolTable, InvalidationListener allWidgetsListener) {
        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(guiQuestion.type);
        LabelWithWidget parent = guiQuestion.render(guiWidget, symbolTable, allWidgetsListener);

        // Apply styles to this widget where applicable
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.getType() == guiQuestion.type) {
                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
                    parent.apply(styleAttribute);
                }
            }
        }

        return parent;
    }
}
