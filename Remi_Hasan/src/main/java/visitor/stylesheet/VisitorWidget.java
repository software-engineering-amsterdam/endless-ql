package visitor.stylesheet;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.stylesheet.widgets.*;

public class VisitorWidget extends QLSBaseVisitor<Widget> {

//    @Override
//    public Widget visitWidget(QLSParser.WidgetContext ctx) {
//        return visit(ctx.WIDGET());
//    }

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        // TODO
        return new WidgetCheckBox();
    }

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        // TODO
        return new WidgetRadio();
    }

    @Override
    public Widget visitSpinBoxWidget(QLSParser.SpinBoxWidgetContext ctx) {
        // TODO
        return new WidgetSpinBox();
    }

    @Override
    public Widget visitWidgetWidth(QLSParser.WidgetWidthContext ctx) {
        return new WidgetWidth();
    }

    @Override
    public Widget visitWidgetFont(QLSParser.WidgetFontContext ctx) {
        return new WidgetFont();
    }

    @Override
    public Widget visitWidgetFontSize(QLSParser.WidgetFontSizeContext ctx) {
        return new WidgetFontSize();
    }

    @Override
    public Widget visitWidgetColor(QLSParser.WidgetColorContext ctx) {
        return new WidgetColor();
    }
}
