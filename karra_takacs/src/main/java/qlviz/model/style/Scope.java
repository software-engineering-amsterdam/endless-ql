package qlviz.model.style;

import java.util.List;

public interface Scope {
    List<DefaultWidgetDeclaration> getDefaultWidgetDeclarations();
    Scope getParent();
    List<? extends Scope> getChildren();
}
