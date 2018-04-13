package QLS.Analysis;

import QLS.AST.Page;
import QLS.AST.Statements.Default;
import QLS.AST.Statements.QLSQuestion;
import QLS.AST.StyleAttribute.Style;
import QLS.AST.Stylesheet;
import QLS.AST.Widgets.Widget;

public interface WidgetVisitorInterface<T> {
    T visit(Stylesheet stylesheet);
    T visit(Page page);
    T visit(QLSQuestion question);
    T visit(Default defaultStyle);

    //Widgets
    T visit(Widget widget);

    //Style attributes
    T visit(Style style);
}
