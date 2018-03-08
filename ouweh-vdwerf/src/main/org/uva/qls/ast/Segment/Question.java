package org.uva.qls.ast.Segment;

import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Widget.Widget;

public class Question extends Segment {

    private String id;
    private Style style;
    private Widget widget;

    public Question(String id, Style style, Widget widget){
        this.id = id;
        this.style = style;
        this.widget = widget;
    }
}
