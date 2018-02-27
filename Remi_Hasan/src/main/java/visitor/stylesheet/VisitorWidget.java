package visitor.stylesheet;

import antlr.QLSBaseVisitor;
import antlr.QLSParser;
import model.stylesheet.widgets.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class VisitorWidget extends QLSBaseVisitor<Widget> {

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        // TODO
        return new WidgetCheckBox();
    }

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        // TODO
        ArrayList<String> options = new ArrayList<>();
        for(TerminalNode stringNode : ctx.STRING()){
            String option = stringNode.getText();
            // Strip quotes
            option = option.substring(1, option.length() - 1);
            options.add(option);
        }
        return new WidgetRadio(options);
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
