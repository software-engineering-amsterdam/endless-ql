package org.uva.qls.ast.Widget.WidgetTypes;

import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class CheckboxType extends WidgetType {

    private String yes;

    public CheckboxType(String yes) {
        this.yes = yes;
    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("BooleanType");
    }

    @Override
    public <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q question, V value, B readOnly) {
        return visitor.visit(this, question, value, readOnly);
    }
}
