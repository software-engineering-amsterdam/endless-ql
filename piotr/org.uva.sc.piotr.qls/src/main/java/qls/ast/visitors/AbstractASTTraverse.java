package qls.ast.visitors;

import qls.ast.model.*;
import qls.ast.model.properties.*;
import qls.ast.model.properties.widgets.*;

public class AbstractASTTraverse<T> implements ASTNodeVisitor<T> {
    @Override
    public T visit(Stylesheet stylesheet) {
        for (Page page : stylesheet.getPages()) {
            page.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Page page) {
        for (BlockElement element : page.getElements()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public T visit(DefaultDefinition defaultDefinition) {
        for (TypeProperty property : defaultDefinition.getTypeProperties()) {
            property.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Section section) {
        for (BlockElement element : section.getElements()) {
            element.accept(this);
        }
        return null;
    }

    @Override
    public T visit(QuestionDefinition questionDefinition) {
        if (questionDefinition.getWidget() != null)
            questionDefinition.getWidget().accept(this);
        return null;
    }

    @Override
    public T visit(CheckboxWidget checkboxWidget) {
        return null;
    }

    @Override
    public T visit(DropdownWidget dropdownWidget) {
        return null;
    }

    @Override
    public T visit(RadioWidget radioWidget) {
        return null;
    }

    @Override
    public T visit(SliderWidget sliderWidget) {
        return null;
    }

    @Override
    public T visit(SpinboxWidget spinboxWidget) {
        return null;
    }

    @Override
    public T visit(TextWidget textWidget) {
        return null;
    }

    @Override
    public T visit(ColorProperty colorProperty) {
        return null;
    }

    @Override
    public T visit(FontProperty fontProperty) {
        return null;
    }

    @Override
    public T visit(FontSizeProperty fontSizeProperty) {
        return null;
    }

    @Override
    public T visit(WidthProperty widthProperty) {
        return null;
    }
}
