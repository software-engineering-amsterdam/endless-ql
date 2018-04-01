package QLS.parsing.visitors;

import QL.parsing.gen.QLBaseVisitor;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.DropdownWidget;
import QLS.classes.widgets.RadioWidget;
import QLS.classes.widgets.SliderWidget;
import QLS.classes.widgets.SpinBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.Widget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

public class WidgetVisitor extends QLSBaseVisitor {

    @Override
    public Widget visitWidget(QLSParser.WidgetContext ctx) {
        return new Widget((WidgetType) visitWidgetType(ctx.widgetType()));
    }


    @Override
    public CheckBoxWidget visitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx) {
        return new CheckBoxWidget();
    }

    @Override
    public TextWidget visitTextWidget(QLSParser.TextWidgetContext ctx) {
        return new TextWidget();
    }

    @Override
    public RadioWidget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        if (ctx.STR().size() != 0) {
            return new RadioWidget(ctx.STR(0).getText(), ctx.STR(1).getText());
        }
        return new RadioWidget();
    }

    @Override
    public SpinBoxWidget visitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx) {
        return new SpinBoxWidget();
    }

    @Override
    public SliderWidget visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        return new SliderWidget();
    }

    @Override
    public DropdownWidget visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        if (ctx.STR().size() != 0) {
            return new DropdownWidget(ctx.STR(0).getText(), ctx.STR(1).getText());
        }
        return new DropdownWidget();
    }
}
