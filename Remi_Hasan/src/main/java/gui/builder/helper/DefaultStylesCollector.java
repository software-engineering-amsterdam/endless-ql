package gui.builder.helper;

import qls.model.QLSNode;
import qls.model.statement.DefaultStyle;
import qls.visitor.QLSVisitor;

import java.util.List;

public class DefaultStylesCollector {

    public static List<DefaultStyle> collect(QLSNode node, List<DefaultStyle> defaultStyles) {
        node.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(DefaultStyle defaultStyle) {
                defaultStyles.add(defaultStyle);

                // Return null, so we don't go into deeper scope level (e.g. styles of a subsection)
                return null;
            }
        });

        return defaultStyles;
    }
}
