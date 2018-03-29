package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.widget.*;

public class WidgetBuilder extends QLSBaseVisitor<Widget> {

    @Override
    public Widget visitRadioWidget(QLSParser.RadioWidgetContext ctx) {
        String trueLabel = ctx.trueLabel.getText();
        String falseLabel = ctx.falseLabel.getText();

        // Strip quotes
        trueLabel = trueLabel.substring(1, trueLabel.length() - 1);
        falseLabel = falseLabel.substring(1, falseLabel.length() - 1);

        RadioWidget radioWidget = new RadioWidget(trueLabel, falseLabel);
        radioWidget.setToken(ctx.getStart());
        return radioWidget;
    }

    @Override
    public Widget visitDropdownWidget(QLSParser.DropdownWidgetContext ctx) {
        String trueLabel = ctx.trueLabel.getText();
        String falseLabel = ctx.falseLabel.getText();

        // Strip quotes
        trueLabel = trueLabel.substring(1, trueLabel.length() - 1);
        falseLabel = falseLabel.substring(1, falseLabel.length() - 1);

        DropDownWidget dropDownWidget = new DropDownWidget(trueLabel, falseLabel);
        dropDownWidget.setToken(ctx.getStart());
        return dropDownWidget;
    }

    @Override
    public Widget visitCheckBoxWidget(QLSParser.CheckBoxWidgetContext ctx) {
        CheckBoxWidget checkBoxWidget = new CheckBoxWidget();
        checkBoxWidget.setToken(ctx.getStart());
        return checkBoxWidget;
    }

    @Override
    public Widget visitSpinBoxWidget(QLSParser.SpinBoxWidgetContext ctx) {
        SpinBoxWidget spinBoxWidget = new SpinBoxWidget();
        spinBoxWidget.setToken(ctx.getStart());
        return spinBoxWidget;
    }

    @Override
    public Widget visitTextBoxWidget(QLSParser.TextBoxWidgetContext ctx) {
        TextBoxWidget textBoxWidget = new TextBoxWidget();
        textBoxWidget.setToken(ctx.getStart());
        return textBoxWidget;
    }

    @Override
    public Widget visitSliderWidget(QLSParser.SliderWidgetContext ctx) {
        double min = Double.parseDouble(ctx.min.getText());
        double max = Double.parseDouble(ctx.max.getText());
        SliderWidget sliderWidget = new SliderWidget(min, max);
        sliderWidget.setToken(ctx.getStart());
        return sliderWidget;
    }


}
