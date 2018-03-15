package org.uva.qls.ast.Widget.WidgetTypes;

import java.util.List;
import java.util.Arrays;

public class CheckboxType extends WidgetType {

    private String yes;

    public CheckboxType(String yes) {
        this.yes = yes;
    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("BooleanType");
    }
}
