package qlviz.interpreter.style;

import org.antlr.v4.runtime.tree.TerminalNode;
import qlviz.model.style.WidgetType;

public class WidgetTypeTranslator {

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public WidgetType translate(TerminalNode node) {
        return WidgetType.valueOf(capitalize(node.getText()));
    }

}
