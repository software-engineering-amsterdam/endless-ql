package qls.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import qls.model.widgets.*;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorWidget extends QLSBaseVisitor<Widget> {

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        // TODO
        return new WidgetCheckBox(ctx.getStart());
    }

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        // TODO
        List<String> options = new ArrayList<>();
        for (TerminalNode stringNode : ctx.STRING()) {
            String option = stringNode.getText();
            // Strip quotes
            option = option.substring(1, option.length() - 1);
            options.add(option);
        }
        return new WidgetRadio(ctx.getStart(), options);
    }

    @Override
    public Widget visitSpinBoxWidget(QLSParser.SpinBoxWidgetContext ctx) {
        // TODO
        return new WidgetSpinBox(ctx.getStart());
    }

    @Override
    public Widget visitWidgetWidth(QLSParser.WidgetWidthContext ctx) {
        int width = Integer.parseInt(ctx.INTEGER().getText());
        return new WidgetWidth(ctx.getStart(), width);
    }

    @Override
    public Widget visitWidgetFont(QLSParser.WidgetFontContext ctx) {
        String fontFamily = ctx.STRING().getText();
        // Strip quotes
        fontFamily = fontFamily.substring(1, fontFamily.length() - 1);
        return new WidgetFont(ctx.getStart(), fontFamily);
    }

    @Override
    public Widget visitWidgetFontSize(QLSParser.WidgetFontSizeContext ctx) {
        int fontSize = Integer.parseInt(ctx.INTEGER().getText());
        return new WidgetFontSize(ctx.getStart(), fontSize);
    }

    @Override
    public Widget visitWidgetColor(QLSParser.WidgetColorContext ctx) {
        String hexColor = ctx.HEXCOLOR().getText();
        return new WidgetColor(ctx.getStart(), hexColor);
    }
}
