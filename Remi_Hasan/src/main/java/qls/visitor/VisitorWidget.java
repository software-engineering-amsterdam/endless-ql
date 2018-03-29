package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.widget.*;

public class VisitorWidget extends QLSBaseVisitor<Widget> {

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        String trueLabel = ctx.trueLabel.getText();
        String falseLabel = ctx.falseLabel.getText();

        // Strip quotes
        trueLabel = trueLabel.substring(1, trueLabel.length() - 1);
        falseLabel = falseLabel.substring(1, falseLabel.length() - 1);

        WidgetRadio widgetRadio = new WidgetRadio(trueLabel, falseLabel);
        widgetRadio.setToken(ctx.getStart());
        return widgetRadio;
    }

    @Override
    public Widget visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        String trueLabel = ctx.trueLabel.getText();
        String falseLabel = ctx.falseLabel.getText();

        // Strip quotes
        trueLabel = trueLabel.substring(1, trueLabel.length() - 1);
        falseLabel = falseLabel.substring(1, falseLabel.length() - 1);

        WidgetDropdown widgetDropdown = new WidgetDropdown(trueLabel, falseLabel);
        widgetDropdown.setToken(ctx.getStart());
        return widgetDropdown;
    }

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        WidgetCheckBox widgetCheckBox = new WidgetCheckBox();
        widgetCheckBox.setToken(ctx.getStart());
        return widgetCheckBox;
    }

    @Override
    public Widget visitSpinBoxWidget(QLSParser.SpinBoxWidgetContext ctx) {
        WidgetSpinBox widgetSpinBox = new WidgetSpinBox();
        widgetSpinBox.setToken(ctx.getStart());
        return widgetSpinBox;
    }

    @Override
    public Widget visitTextBoxWidget(QLSParser.TextBoxWidgetContext ctx) {
        WidgetTextBox widgetTextBox = new WidgetTextBox();
        widgetTextBox.setToken(ctx.getStart());
        return widgetTextBox;
    }

    @Override
    public Widget visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        double min = Double.parseDouble(ctx.min.getText());
        double max = Double.parseDouble(ctx.max.getText());
        WidgetSlider widgetSlider = new WidgetSlider(min, max);
        widgetSlider.setToken(ctx.getStart());
        return widgetSlider;
    }


}
