package org.uva.qls.ast.Widget.WidgetTypes;


import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class TextType extends WidgetType {

    public TextType() {

    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("StringType", "IntegerType");
    }

    @Override
    public <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q question, V value, B readOnly) {
        return visitor.visit(this, question, value, readOnly);
    }

}
