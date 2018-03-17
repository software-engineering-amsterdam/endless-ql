package qls.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import qls.model.widget.*;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

import java.util.ArrayList;
import java.util.List;

public class VisitorWidget extends QLSBaseVisitor<Widget> {

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        return new WidgetCheckBox(ctx.getStart());
    }

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
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
        return new WidgetSpinBox(ctx.getStart());
    }
}
