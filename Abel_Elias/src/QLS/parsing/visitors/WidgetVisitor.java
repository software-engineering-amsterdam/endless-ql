package QLS.parsing.visitors;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.NumericValue;
import QL.classes.values.StringValue;
import QL.classes.values.Value;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;
import gui.widgets.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WidgetVisitor extends QLSBaseVisitor {
    private Value currentValue;

    public Widget visitWidget(QLSParser.WidgetContext ctx, Value value) {
        this.currentValue = value;
        return (Widget) super.visitWidgetType(ctx.widgetType());
    }

    @Override
    public CheckBoxWidget visitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx) {

        return new CheckBoxWidget((BooleanValue) currentValue);
    }

    @Override
    public SpinBoxWidget visitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx) {
        return new SpinBoxWidget((NumericValue) currentValue);
    }

    @Override
    public TextWidget visitTextWidget(QLSParser.TextWidgetContext ctx) {
        return new TextWidget((StringValue) currentValue);
    }

    @Override
    public Object visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        ArrayList<String> options = new ArrayList<>();

        for(TerminalNode t : ctx.argList().STR()){
            options.add(t.getText());
        }

        return new RadioWidget(currentValue, options.toArray());
    }


    @Override
    public Object visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        //TODO
        return super.visitSliderWidget(ctx);
    }

    @Override
    public DropDownWidget visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        ArrayList<String> options = new ArrayList<>();

        for(TerminalNode t : ctx.argList().STR()){
            options.add(t.getText());
        }

        return new DropDownWidget(currentValue, options.toArray());
    }
}