package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetTextBox extends Widget {
    public WidgetTextBox(Token start) {
        super(start, WidgetType.TEXTBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
        switch(question.type){
            case INTEGER:
                return visitor.visitWidgetTypeInteger(symbolTable, question, defaultStyles);
            case DECIMAL:
                return visitor.visitWidgetTypeDecimal(symbolTable, question, defaultStyles);
            case MONEY:
                return visitor.visitWidgetTypeMoney(symbolTable, question, defaultStyles);
            default:
                throw new UnsupportedOperationException("Unknown type for textbox");
        }
    }
}
