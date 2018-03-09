package org.uva.qls.ast.Widget.WidgetTypes;


public class SliderType extends WidgetType {

    private String start;
    private String end;
    private String step;

    public SliderType(String start, String end, String step){
        this.start = start;
        this.end = end;
        this.step = step;
    }

}
