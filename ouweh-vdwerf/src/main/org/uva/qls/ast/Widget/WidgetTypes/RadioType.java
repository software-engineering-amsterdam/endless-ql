package org.uva.qls.ast.Widget.WidgetTypes;

import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class RadioType extends WidgetType {

    private String trueLabel;
    private String falseLabel;

    public RadioType(String trueLabel, String falseLabel) {
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    public String getFalseLabel() {
        return falseLabel;
    }

    public String getTrueLabel() {
        return trueLabel;
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
