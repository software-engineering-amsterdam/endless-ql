package qls.visitor;

import qls.model.widget.*;
import qls.parser.QLSBaseVisitor;
import qls.parser.QLSParser;

public class VisitorWidget extends QLSBaseVisitor<Widget> {

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        return new WidgetCheckBox(ctx.getStart());
    }

    @Override
    public Widget visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        String trueLabel = ctx.trueLabel.getText();
        String falseLabel = ctx.falseLabel.getText();

        // Strip quotes
        trueLabel = trueLabel.substring(1, trueLabel.length() - 1);
        falseLabel = falseLabel.substring(1, falseLabel.length() - 1);

        return new WidgetDropdown(ctx.getStart(), trueLabel, falseLabel);
    }

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        String trueLabel = ctx.trueLabel.getText();
        String falseLabel = ctx.falseLabel.getText();

        // Strip quotes
        trueLabel = trueLabel.substring(1, trueLabel.length() - 1);
        falseLabel = falseLabel.substring(1, falseLabel.length() - 1);

        return new WidgetRadio(ctx.getStart(), trueLabel, falseLabel);
    }

    @Override
    public Widget visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        double min = Double.parseDouble(ctx.min.getText());
        double max = Double.parseDouble(ctx.max.getText());
        double step = Double.parseDouble(ctx.step.getText());
        return new WidgetSlider(ctx.getStart(), min, max, step);
    }

    @Override
    public Widget visitSpinBoxWidget(QLSParser.SpinBoxWidgetContext ctx) {
        return new WidgetSpinBox(ctx.getStart());
    }

    @Override
    public Widget visitTextBoxWidget(QLSParser.TextBoxWidgetContext ctx) {
        return new WidgetTextBox(ctx.getStart());
    }


}
