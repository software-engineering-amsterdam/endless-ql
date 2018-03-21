package org.uva.qls.ast.Widget.WidgetTypes;


import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class SpinboxType extends WidgetType {

    public SpinboxType() {

    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("IntegerTyp");
    }

    @Override
    public <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q question, V value, B readOnly) {
        return visitor.visit(this, question, value, readOnly);
    }

}
