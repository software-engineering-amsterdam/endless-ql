package org.uva.qls.ast.Widget;

import org.uva.qls.ast.TreeNode;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;

public class Widget extends TreeNode {

    private WidgetType type;

    public Widget(WidgetType type){
        this.type = type;
    }

    public boolean isCompatible(String questionType) {
        return this.type.getCompatibleTypes().contains(questionType);
    }
}
