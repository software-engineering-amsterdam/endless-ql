package org.uva.qls.ast.Widget.WidgetTypes;


import java.util.Arrays;
import java.util.List;

public class SpinboxType extends WidgetType {

    public SpinboxType(){

    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("IntegerTyp");
    }

}
