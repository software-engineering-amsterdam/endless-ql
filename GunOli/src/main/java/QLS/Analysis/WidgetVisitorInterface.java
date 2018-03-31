package QLS.Analysis;

import QLS.ParseObjectQLS.*;
import QLS.ParseObjectQLS.StyleAttribute.*;
import QLS.ParseObjectQLS.Widgets.*;

public interface WidgetVisitorInterface<T> {
    T visit(Stylesheet Styelsheet);
    T visit(Section section);
    T visit(Page page);
    T visit(QLSQuestion question);
    T visit(Default defaultStyle);
    T visit(Widget widget);

    //Style attributes
    T visit(Font widget);
    T visit(FontSize widget);
    T visit(Width widget);
    T visit(Color widget);

    //Widgets
    T visit(CheckBox widget);
    T visit(DropDown widget);
    T visit(Radio widget);
    T visit(Slider widget);
    T visit(SpinBox widget);
    T visit(Text widget);


}
