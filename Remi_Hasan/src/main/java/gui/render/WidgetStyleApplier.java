package gui.render;

import gui.elements.LabelWithWidget;
import ql.model.expression.ReturnType;
import qls.QLSVisitor;
import qls.model.statement.DefaultStyle;
import qls.model.style.*;

import java.util.List;

public class WidgetStyleApplier extends QLSVisitor<Void> {

    private LabelWithWidget labelWithWidget;

    public void applyStyles(LabelWithWidget labelWithWidget, List<DefaultStyle> defaultStyles, ReturnType questionType) {
        this.labelWithWidget = labelWithWidget;

        for (DefaultStyle defaultStyle : defaultStyles) {
            // Apply style if applicable to this question type
            if (defaultStyle.getType() == questionType) {
                for (StyleAttribute styleAttribute : defaultStyle.getStyleAttributes()) {
                    styleAttribute.accept(this);
                }
            }
        }
    }

    @Override
    public Void visit(DefaultStyle defaultStyle) {
        return defaultStyle.accept(this);
    }

    @Override
    public Void visit(StyleAttributeColor style) {
        labelWithWidget.setColor(style.getColor());
        return super.visit(style);
    }

    @Override
    public Void visit(StyleAttributeFont style) {
        labelWithWidget.setFont(style.getFontFamily());
        return super.visit(style);
    }

    @Override
    public Void visit(StyleAttributeFontSize style) {
        labelWithWidget.setFontSize(style.getFontSize());
        return super.visit(style);
    }

    @Override
    public Void visit(StyleAttributeWidth style) {
        labelWithWidget.setWidth(style.getWidth());
        return super.visit(style);
    }
}
