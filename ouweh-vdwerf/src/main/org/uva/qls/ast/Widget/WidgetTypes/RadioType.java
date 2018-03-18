package org.uva.qls.ast.Widget.WidgetTypes;

import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class RadioType extends WidgetType {

    private String yes;
    private String no;

    public RadioType(String yes, String no) {
        this.yes = yes;
        this.no = no;
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
