package org.uva.qls.ast.Widget.WidgetTypes;

import org.uva.qls.ast.TreeNode;
import java.util.List;

public abstract class WidgetType extends TreeNode {

    public abstract List<String> getCompatibleTypes();

}
