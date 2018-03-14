package org.uva.qls.ast.Widget;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.ast.Segment.Segment;
import org.uva.qls.ast.TreeNode;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;

import java.util.List;

public class Widget extends TreeNode {

    private WidgetType type;

    public Widget(WidgetType type){
        this.type = type;
    }
}
