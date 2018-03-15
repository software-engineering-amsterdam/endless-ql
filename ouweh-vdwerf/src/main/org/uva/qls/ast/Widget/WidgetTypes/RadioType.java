package org.uva.qls.ast.Widget.WidgetTypes;

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

}
