package gui.render;

import gui.elements.LabelWithWidget;
import ql.model.expression.ReturnType;
import qls.model.statement.DefaultStyle;
import qls.model.style.*;
import qls.visitor.QLSVisitor;

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
    public Void visit(ColorAttribute style) {
        labelWithWidget.setColor(style.getColor());
        return super.visit(style);
    }

    @Override
    public Void visit(FontStyleAttribute style) {
        labelWithWidget.setFont(style.getFontFamily());
        return super.visit(style);
    }

    @Override
    public Void visit(FontSizeAttribute style) {
        labelWithWidget.setFontSize(style.getFontSize());
        return super.visit(style);
    }

    @Override
    public Void visit(WidthAttribute style) {
        labelWithWidget.setWidth(style.getWidth());
        return super.visit(style);
    }
}
