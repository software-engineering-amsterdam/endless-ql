package qls.ast.visitors;

import qls.ast.model.*;
import qls.ast.model.properties.ColorProperty;
import qls.ast.model.properties.FontProperty;
import qls.ast.model.properties.FontSizeProperty;
import qls.ast.model.properties.WidthProperty;
import qls.ast.model.properties.widgets.*;

public interface ASTNodeVisitor<T> {
    T visit(Stylesheet stylesheet);

    T visit(Page pageDefinition);

    T visit(DefaultDefinition defaultDefinition);

    T visit(Section section);

    T visit(QuestionDefinition questionDefinition);

    T visit(CheckboxWidget checkboxWidget);

    T visit(DropdownWidget dropdownWidget);

    T visit(RadioWidget radioWidget);

    T visit(SliderWidget sliderWidget);

    T visit(SpinboxWidget spinboxWidget);

    T visit(TextWidget textWidget);

    T visit(ColorProperty colorProperty);

    T visit(FontProperty fontProperty);

    T visit(FontSizeProperty fontSizeProperty);

    T visit(WidthProperty widthProperty);
}
