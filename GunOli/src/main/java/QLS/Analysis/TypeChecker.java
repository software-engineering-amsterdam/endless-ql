package QLS.Analysis;

import QLS.ParseObjectQLS.*;
import QLS.ParseObjectQLS.StyleAttribute.Color;
import QLS.ParseObjectQLS.StyleAttribute.Font;
import QLS.ParseObjectQLS.StyleAttribute.FontSize;
import QLS.ParseObjectQLS.StyleAttribute.Width;
import QLS.ParseObjectQLS.Widgets.*;

import java.lang.reflect.Type;

public class TypeChecker implements WidgetVisitorInterface<WidgetType>{

    Stylesheet stylesheet;

    public TypeChecker(Stylesheet stylesheet){
        this.stylesheet = stylesheet;

    }


    @Override
    public WidgetType visit(Stylesheet Styelsheet) {
        return null;
    }

    @Override
    public WidgetType visit(Section section) {
        return null;
    }

    @Override
    public WidgetType visit(Page page) {
        return null;
    }

    @Override
    public WidgetType visit(QLSQuestion question) {
        return null;
    }

    @Override
    public WidgetType visit(Default defaultStyle) {
        return null;
    }

    @Override
    public WidgetType visit(Widget widget) {
        return null;
    }

    @Override
    public WidgetType visit(Font widget) {


        return null;
    }

    @Override
    public WidgetType visit(FontSize widget) {
        return null;
    }

    @Override
    public WidgetType visit(Width widget) {
        return null;
    }

    @Override
    public WidgetType visit(Color widget) {
        return null;
    }

    @Override
    public WidgetType visit(CheckBox widget) {
        return null;
    }

    @Override
    public WidgetType visit(DropDown widget) {
        return null;
    }

    @Override
    public WidgetType visit(Radio widget) {
        return null;
    }

    @Override
    public WidgetType visit(Slider widget) {
        return null;
    }

    @Override
    public WidgetType visit(SpinBox widget) {
        return null;
    }

    @Override
    public WidgetType visit(Text widget) {
        return null;
    }
}
