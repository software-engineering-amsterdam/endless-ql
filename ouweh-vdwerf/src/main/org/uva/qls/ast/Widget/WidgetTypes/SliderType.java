package org.uva.qls.ast.Widget.WidgetTypes;


import java.util.Arrays;
import java.util.List;

public class SliderType extends WidgetType {

    private String start;
    private String end;
    private String step;

    public SliderType(String start, String end, String step){
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public List<String> getCompatibleTypes() {
        return Arrays.asList("IntegerType");
    }

}
