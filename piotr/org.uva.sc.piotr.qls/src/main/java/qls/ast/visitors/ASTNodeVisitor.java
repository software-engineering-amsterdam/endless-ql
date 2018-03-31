package qls.ast.visitors;

import qls.ast.model.*;
import qls.ast.model.properties.*;

public interface ASTNodeVisitor<T> {
    T visit(Stylesheet stylesheet);

    T visit(Page pageDefinition);

    T visit(DefaultDefinition defaultDefinition);

    T visit(BlockElement blockElement);

    T visit(Section section);

    T visit(QuestionDefinition questionDefinition);

    T visit(Widget widget);

    T visit(ColorProperty colorProperty);

    T visit(FontProperty fontProperty);

    T visit(FontSizeProperty fontSizeProperty);

    T visit(WidthProperty widthProperty);
}
