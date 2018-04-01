package qls.ast.visitors;

import qls.ast.model.*;
import qls.ast.model.properties.ColorProperty;
import qls.ast.model.properties.FontProperty;
import qls.ast.model.properties.FontSizeProperty;
import qls.ast.model.properties.WidthProperty;
import qls.ast.model.properties.widgets.*;

public class TestASTTraverse extends AbstractASTTraverse<Object> {
    @Override
    public Object visit(Stylesheet stylesheet) {
        System.out.println("Visiting stylesheet");
        return super.visit(stylesheet);
    }

    @Override
    public Object visit(Page page) {
        System.out.println("Visiting page");
        return super.visit(page);
    }

    @Override
    public Object visit(DefaultDefinition defaultDefinition) {
        System.out.println("Visiting defaultDefinition");
        return super.visit(defaultDefinition);
    }

    @Override
    public Object visit(Section section) {
        System.out.println("Visiting section");
        return super.visit(section);
    }

    @Override
    public Object visit(QuestionDefinition questionDefinition) {
        System.out.println("Visiting questionDefinition");
        return super.visit(questionDefinition);
    }

    @Override
    public Object visit(CheckboxWidget checkboxWidget) {
        System.out.println("Visiting checkboxWidget");
        return super.visit(checkboxWidget);
    }

    @Override
    public Object visit(DropdownWidget dropdownWidget) {
        System.out.println("Visiting dropdownWidget");
        return super.visit(dropdownWidget);
    }

    @Override
    public Object visit(RadioWidget radioWidget) {
        System.out.println("Visiting radioWidget");
        return super.visit(radioWidget);
    }

    @Override
    public Object visit(SliderWidget sliderWidget) {
        System.out.println("Visiting sliderWidget");
        return super.visit(sliderWidget);
    }

    @Override
    public Object visit(SpinboxWidget spinboxWidget) {
        System.out.println("Visiting spinboxWidget");
        return super.visit(spinboxWidget);
    }

    @Override
    public Object visit(TextWidget textWidget) {
        System.out.println("Visiting textWidget");
        return super.visit(textWidget);
    }

    @Override
    public Object visit(ColorProperty colorProperty) {
        System.out.println("Visiting colorProperty");
        return super.visit(colorProperty);
    }

    @Override
    public Object visit(FontProperty fontProperty) {
        System.out.println("Visiting fontProperty");
        return super.visit(fontProperty);
    }

    @Override
    public Object visit(FontSizeProperty fontSizeProperty) {
        System.out.println("Visiting fontSizeProperty");
        return super.visit(fontSizeProperty);
    }

    @Override
    public Object visit(WidthProperty widthProperty) {
        System.out.println("Visiting widthProperty");
        return super.visit(widthProperty);
    }
}
