package gui.model;

import gui.elements.LabelWithWidget;
import javafx.scene.Parent;
import ql.evaluation.SymbolTable;
import qls.model.DefaultStyle;
import qls.model.style.StyleAttribute;

import java.util.List;

public class GUIQuestionWithStyling {

    private final GUIQuestion guiQuestion;
    private final List<DefaultStyle> defaultStyles;

    public GUIQuestionWithStyling(GUIQuestion guiQuestion, List<DefaultStyle> defaultStyles) {
        this.guiQuestion = guiQuestion;
        this.defaultStyles = defaultStyles;
    }

    public Parent render(SymbolTable symbolTable){
        LabelWithWidget parent = guiQuestion.render(symbolTable, null);
        for(DefaultStyle defaultStyle : defaultStyles){
            if(defaultStyle.type == this.guiQuestion.type) {
                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
                    parent.apply(styleAttribute);
                }
            }
        }

        return parent;
    }
}
