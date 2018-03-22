package qls.model.widget;

import gui.WidgetVisitor;
import gui.widgets.WidgetInterface;
import org.antlr.v4.runtime.Token;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.model.DefaultStyle;
import qls.model.QLSNode;

import java.util.List;

public abstract class Widget extends QLSNode {

    public final WidgetType type;

    Widget(Token start, WidgetType type) {
        super(start);
        this.type = type;
    }

    public abstract WidgetInterface createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
}