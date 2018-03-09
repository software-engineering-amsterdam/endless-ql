package org.uva.qls.ast.Style;

import org.uva.qls.ast.Style.StyleProperty.StyleProperty;
import org.uva.qls.ast.TreeNode;

import java.util.List;

public class Style extends TreeNode {

    List<StyleProperty> styleProperties;

    public Style(List<StyleProperty> styleProperties){
        this.styleProperties = styleProperties;
    }
}
