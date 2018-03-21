package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.IQLSVisitor;
import qls.model.DefaultStyle;

import java.util.List;

public class WidgetDatePicker extends Widget {

    WidgetDatePicker(Token start) {
        super(start, WidgetType.DATE);
    }

    @Override
    public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
        return visitor.visitWidgetTypeDate(symbolTable, question, qlsQuestion, defaultStyles);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
