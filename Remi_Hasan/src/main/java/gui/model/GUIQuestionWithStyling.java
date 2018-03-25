package gui.model;

import gui.elements.LabelWithWidget;
import javafx.scene.Parent;
import ql.evaluation.SymbolTable;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import qls.model.DefaultStyle;
import qls.model.style.StyleAttribute;

import java.util.List;

public class GUIQuestionWithStyling extends GUIQuestion {

    private final List<DefaultStyle> defaultStyles;

    // TODO: too many parameters
    public GUIQuestionWithStyling(String identifier, String label, ReturnType type, Expression condition, Expression computedAnswer, List<DefaultStyle> defaultStyles) {
        super(identifier, label, type, condition, computedAnswer);
        this.defaultStyles = defaultStyles;
    }

    public Parent render(SymbolTable symbolTable){
        LabelWithWidget parent = super.render(symbolTable, null);
//        for(DefaultStyle defaultStyle : defaultStyles){
//            if(defaultStyle.type == this.guiQuestion.type) {
//                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
//                    parent.apply(styleAttribute);
//                }
//            }
//        }

        return parent;
    }
}
